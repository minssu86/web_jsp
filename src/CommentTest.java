import java.util.List;

import com.minsu.dto.BoardResponseDto;
import com.minsu.dto.CommentRequestDto;
import com.minsu.dto.CommentResponseDto;
import com.minsu.service.CommentService;

public class CommentTest {

	String result = "";
	CommentService commentService = new CommentService();
	CommentRequestDto commentRequestDto = new CommentRequestDto();
	int userSeq;
	int brdSeq;
	int page;
	int size;
	
	public CommentTest(int userSeq, int brdSeq, int page, int size) {
		this.userSeq = userSeq;
		this.brdSeq = brdSeq;
		this.page = page;
		this.size = size;
		
	}
	
	public void createComment(int brdSeq, int parentSeq) {
		commentRequestDto.setBrdSeq(brdSeq);
		commentRequestDto.setCmtParentSeq(parentSeq);
		commentRequestDto.setContent("댓글 테스트 입니다3");
		result = commentService.createComment(userSeq, commentRequestDto);
		System.out.println("댓글 생성 테스트 : " + result);
	}
	
	public void getCommentList() {
		List<CommentResponseDto> commentResponseDtos = commentService.getCommentList(page, size, brdSeq, userSeq);
		if(commentResponseDtos.size()>0) {
			for(CommentResponseDto commentResponseDto : commentResponseDtos) {
				System.out.println("cmtSeq : "+ commentResponseDto.getCmtSeq());
				System.out.println("cmtContent : "+ commentResponseDto.getCmtContent());
				System.out.println("date : "+ commentResponseDto.getDate());
				System.out.println("cmtParentSeq : "+ commentResponseDto.getCmtParentSeq());
				System.out.println("cmtLikeCount : "+ commentResponseDto.getCmtLikeCount());
				System.out.println("cmtIsDeleted : "+ commentResponseDto.isCmtIsDeleted());
				System.out.println("cmtIsModify : "+ commentResponseDto.isCmtIsModify());
				System.out.println("brdSeq : "+ commentResponseDto.getBrdSeq());
				System.out.println("userNickname : "+ commentResponseDto.getUserNickname());
				System.out.println("isLiked : "+ commentResponseDto.isLiked());
				System.out.println("isMine : "+ commentResponseDto.isMine());
				System.out.println("=======================================================");
			}
		} else {
			System.out.println("페이지가 없습니다");
		}
	}
	
	public void updateComment(int cmtSeq) {
		commentRequestDto.setContent("게시글 수정 테스트 제목");
		commentRequestDto.setCmtSeq(cmtSeq);
		result = commentService.changeComment(commentRequestDto, userSeq);
		System.out.println("댓글 수정 테스트 : " + result);
	}

	public void deleteComment(int brdSeq, int cmdSeq, int userSeq) {
		commentRequestDto.setBrdSeq(brdSeq);
		commentRequestDto.setCmtSeq(cmdSeq);
		result = commentService.deleteComment(commentRequestDto, userSeq);
		System.out.println("댓글 삭제 테스트 : " + result);
	}

	public void likeBoard(int cmdSeq, int userSeq) {
		result = commentService.likeComment(cmdSeq, userSeq);
		System.out.println("게시글 좋아요 테스트 : " + result);
	}
	
}
