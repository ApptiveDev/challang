 # 찰랑
(개요, 프로젝트 소개)

 # 📝 주요기능

 # 🔨 기술스택
(백엔드, 프론트, 협업에 사용한 툴, 라이브러리, 프레임워크)

 # 🤝 개발협업
 ## 🌲 Branch 
```
main ------- backend/<이름>/<이슈번호>-<작업명>    (백엔드 작업)
     \------ frontend/<이름>/<이슈번호>-<작업명>   (프론트 작업)

ex) backend/wonseok/#10-add-animation
ex) frontend/wonseok/fix-login-not-allowed   (이슈가 없으면)
```
브랜치 관리 전략은 `main`과 개인 브랜치만이 존재하는 간단한 **Github Flow**를 따릅니다.
- `main` 브랜치는 항상 작동 가능한 안정된 상태여야 한다.
  - 직접 커밋하지 않으며, Pull Request만으로 변경한다.
- 개인 브랜치에서 작업을 진행한다.
- 브랜치명은 작업 내용과 직군이 구체적으로 드러나도록 한다.
  - 브랜치명에 `backend`, `frontend`를 구분한다.
  - 띄어쓰기는 하이픈(`-`)으로 구분한다.
  - 브랜치명은 전부 소문자를 사용한다.

 ## 🍪 Pull Request
```
main    ---●---●---●---------● abc (Squash Merge)
                \           /
개인브랜치          a---b---c   ('abc' 합쳐진 하나의 커밋으로 병합)
```
`main` 브랜치의 커밋은 Pull Request 단위로 쌓으며 이를 위해 **Squash Merge**를 원칙으로 합니다. **Squash Merge**는 브랜치가 병합될 때 커밋들이 PR 제목으로 합쳐지게 됩니다. 커밋은 개인마다 기준이 조금씩 다른 반면, PR/브랜치는 이슈 단위로 생성하므로 일관된 기준으로 커밋을 쌓을 수 있어 히스토리 추적을 용이하게 합니다.
- 커밋 제목은 **PR 제목**으로 한다.
    - 작업 내용을 구체적으로 드러나게 적는다.
- 커밋 내용은 **PR 내용**으로 한다.
    - 브랜치에서의 변경점을 상세히 적는다.
- Pull Request는 작은 작업 단위(200줄 이내 권장)로 한다.


 # 🛠 설치방법
(다른 개발자가 이 프로젝트를 테스트해볼 수 있도록 프론트, 백엔드 설치/실행 절차 안내)

 # 🖼️ 스크린샷

 # 🧑‍💻 팀원
| <img width="100" src="https://github.com/best11gh.png"> | <img width="100" src="https://github.com/dadaruda.png"> | <img width="100" src="https://github.com/betapa.png"> | <img width="100" src="https://github.com/FiveStarK.png"> |
|:----------------------:|:----------------------:|:----------------------:|:----------------------:|
| [이가현](https://github.com/best11gh) | [김다현](https://github.com/dadaruda) | [강태훈](https://github.com/betapa) | [권오성](https://github.com/FiveStarK) |
| 💻 Backend | 💻 Frontend | 💻 Backend | 💻 Frontend |
| 21기 | 21기 | 21기 | 21기 |

 
