# ski-sharing
스키 공유 서비스
- ERD : https://www.erdcloud.com/d/zszsdwRqB2PR3nMxu
- API SHEET : https://docs.google.com/spreadsheets/d/1fTIWcbuxU-xtftxc7pVbGo4eLdu0GnWNc1UD1-L2hX0/edit#gid=0

# 서비스 탄생 및 목적
1. 장비가 없는 사람 : 기존 스키, 보드등의 장비들을 대여시 가격적으로 부담
2. 장비가 있는 사람 : 스키를 타려면 기본적으로 좀 멀리 나가야됨 -> 자주 가기에는 부담 -> 집에 장비들이 방치되는 경우가 많음
3. 장비가 있는 사람은 장비를 빌려주고 돈을 받을 수 있고, 장비가 없는 사람은 장비가 있는 사람한테 저렴한 가격으로 대여 가능한 서비스를 제공하는 것이 목적

# 고려해야할 핵심 기능
1. 장비를 빌려준다는 게시물 기능
2. 장비를 서로 빌려주고 돈을 받고 다시 되돌려주는 일련의 과정들을 증명할 수 있는 기능

# 스펙
자바 11 / 스프링 부트 2.7.8 / JPA / MySQL / S3 / Swagger
