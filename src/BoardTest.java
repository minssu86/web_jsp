//import java.util.List;
//
//import com.minsu.dto.BoardRequestDto;
//import com.minsu.dto.BoardResponseDto;
//import com.minsu.service.BoardService;
//
//public class BoardTest {
//	
//	String result = "";
//	BoardService boardService = new BoardService();
//	BoardRequestDto boardRequestDto = new BoardRequestDto();
//	List<BoardResponseDto> boardResponseDtos = null;
//	int userSeq;
//	int brdSeq;
//	int page;
//	int size;
//	
//	public BoardTest(int userSeq, int brdSeq, int page, int size) {
//		this.userSeq = userSeq;
//		this.brdSeq = brdSeq;
//		this.page = page;
//		this.size = size;
//	}
//	
//	
////	 게시글 생성 테스트
//	public void createBoardTest(int userSeq) {
//		boardRequestDto.setBrdTitle("게시글 생성 테스트 제목");
//		boardRequestDto.setBrdContent("게시글 생성 테스트 내용");
//		result = boardService.createBoard(userSeq, boardRequestDto);
//		System.out.println("게시글 생성 테스트 : " + result);
//	}
//	
//	// 게시글 리스트 조회 테스트
//	public void getBoardList() {
//		boardResponseDtos = boardService.getBoardList(page, size);
//		if(boardResponseDtos.size()>0) {
//			for(BoardResponseDto boardResponseDto : boardResponseDtos) {
//				System.out.println("brdSeq : "+ boardResponseDto.getBrdSeq());
//				System.out.println("brdTitle : "+ boardResponseDto.getBrdTitle());
//				System.out.println("userNickname : "+ boardResponseDto.getUserNickname());
//				System.out.println("date : "+ boardResponseDto.getDate());
//				System.out.println("viewCount : "+ boardResponseDto.getViewCount());
//				System.out.println("likeCount : "+ boardResponseDto.getLikeCount());
//			}
//		} else {
//			System.out.println("페이지가 없습니다");
//		}
//	}
//
//
//	// 게시글 상세 페이지 조회
//	public void getBoard() {
//		BoardResponseDto boardResponseDto = boardService.getBoard(brdSeq, userSeq);
//		if(boardResponseDto!=null) {
//			System.out.println("brdSeq : " + boardResponseDto.getBrdSeq());
//			System.out.println("brdTitle : " + boardResponseDto.getBrdTitle());
//			System.out.println("brdContent : " + boardResponseDto.getBrdContent());
//			System.out.println("userNickname : " + boardResponseDto.getUserNickname());
//			System.out.println("date : " + boardResponseDto.getDate());
//			System.out.println("viewCount : " + boardResponseDto.getViewCount());
//			System.out.println("likeCount : " + boardResponseDto.getLikeCount());
//			System.out.println("commentCount : " + boardResponseDto.getCommentCount());
//			System.out.println("isLiked : " + boardResponseDto.isLiked());
//			System.out.println("isMine : " + boardResponseDto.isMine());
//		} else {
//			System.out.println("페이지가 없습니다");
//		}
//	}
//
//
//	// 게시글 수정
//	public void updateBoard(int brdSeq, int userSeq) {
//		boardRequestDto.setBrdTitle("게시글 수정 테스트 제목");
//		boardRequestDto.setBrdContent("게시글 수정 테스트 내용");
//		result = boardService.changeBoard(boardRequestDto, brdSeq, userSeq);
//		System.out.println("게시글 수정 테스트 : " + result);
//	}
//
//
//	// 게시글 삭제
//	public void deleteBoard(int brdSeq, int userSeq) {
//		result = boardService.deleteBoard(brdSeq, userSeq);
//		System.out.println("게시글 삭제 테스트 : " + result);
//	}
//
//
//	// 게시글 좋아요
//	public void likeBoard(int brdSeq, int userSeq) {
//		result = boardService.likeBoard(brdSeq, userSeq);
//		System.out.println("게시글 좋아요 테스트 : " + result);
//	}
//
//	
//	// 키워드 검색
//	public void searchKeyword(int page, int size, String keyword) {
//		boardResponseDtos = boardService.searchKeyword(page, size, keyword);
//		if(boardResponseDtos.size()>0) {
//			for(BoardResponseDto boardResponseDto : boardResponseDtos) {
//				System.out.println("brdSeq : "+ boardResponseDto.getBrdSeq());
//				System.out.println("brdTitle : "+ boardResponseDto.getBrdTitle());
//				System.out.println("userNickname : "+ boardResponseDto.getUserNickname());
//				System.out.println("date : "+ boardResponseDto.getDate());
//				System.out.println("viewCount : "+ boardResponseDto.getViewCount());
//				System.out.println("likeCount : "+ boardResponseDto.getLikeCount());
//			}
//		} else {
//			System.out.println("페이지가 없습니다");
//		}
//	}
//	
//}
