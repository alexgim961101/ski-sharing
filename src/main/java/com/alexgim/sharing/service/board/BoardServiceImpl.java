package com.alexgim.sharing.service.board;

import com.alexgim.sharing.domain.board.Board;
import com.alexgim.sharing.domain.board.BoardRepository;
import com.alexgim.sharing.domain.image.Image;
import com.alexgim.sharing.domain.image.ImageRepository;
import com.alexgim.sharing.domain.user.User;
import com.alexgim.sharing.domain.user.UserRepository;
import com.alexgim.sharing.handler.ex.BaseException;
import com.alexgim.sharing.handler.ex.BaseResponseStatus;
import com.alexgim.sharing.util.S3Component;
import com.alexgim.sharing.web.dto.board.PostBoardReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final S3Component s3;

    @Transactional
    @Override
    public Board save(Long userId, PostBoardReq postBoardReq) {
        User userEntity = null;
        try {
            userEntity = userRepository.findById(userId).get();
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }
        Board board = postBoardReq.toEntity(userEntity);
        Board boardEntity = null;
        try {
            boardEntity = boardRepository.save(board);
        } catch (Exception e) {
            throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
        }

        List<Image> imageList = new ArrayList<>();
        for(MultipartFile file : postBoardReq.getFileList()) {
            String originalFilename = file.getOriginalFilename();
            String url = null;
            try {
                url = s3.saveFile(file);
            } catch (Exception e) {
                throw new BaseException(BaseResponseStatus.AWS_S3_CONNECTION_FAILED);
            }
            try {
                Image imageEntity = imageRepository.save(Image.builder()
                        .board(boardEntity)
                        .name(originalFilename)
                        .url(url).build());
                imageList.add(imageEntity);
                boardEntity.setImageList(imageList);
                boardRepository.save(boardEntity);
            } catch (Exception e) {
                throw new BaseException(BaseResponseStatus.DB_CONNECTION_ERROR);
            }
        }

        return boardEntity;
    }

//    @Override
//    public void readAll(Pageable pageable) {
//        boardRepository.findAll(pageable);
//    }


}
