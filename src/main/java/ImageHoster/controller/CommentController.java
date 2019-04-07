package ImageHoster.controller;


import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private CommentService commentService;

//This controller method is called when the request pattern is of type '/image/{id}/{title}/comments' and also the incoming request is of POST type
    //The method receives all the details of the comment to be stored in the database, and now the comment will be sent to the business logic to be persisted in the database
    //set the user of the image by getting the logged in user from the Http Session
    //set the image of the comment
    //Set the date on which the comment is posted
    //After storing the comment, this method directs to the image page

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String uploadComment(@RequestParam(name = "comment") String comment, @PathVariable("imageTitle") String imageTitle, @PathVariable("imageId") Integer imageId, Comment newcomment, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggeduser");
        //setting the comment model class
        Image image = imageService.getImageByTitle(imageId, imageTitle);
        newcomment.setUser(user);
        newcomment.setImage(image);
        newcomment.setCreatedDate(new Date());
        newcomment.setText(comment);
        //registering the new comment in the database with help of comment service
        commentService.postComment(newcomment);

        //returning the same image page after post request
        model.addAttribute("comments", image.getComments());
        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());
        return "images/image";
    }
}