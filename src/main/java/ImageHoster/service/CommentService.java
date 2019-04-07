package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    //Call the postComment() method in the CommentRepository class to persist the comment record in the database
    public void postComment(Comment post){

        commentRepository.postComment(post);

    }
}
