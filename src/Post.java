import java.io.*;
import java.util.*;

public class Post
{
    private String post;
    private String user;
    private int likes;
    private LnkList likers;

    public Post(String inPost, String inUser)
    {
        post = inPost;
        user = inUser;
        likes = 0;
        likers = new LnkList();
    }

    public Post(String inPost)
    {
        post = inPost;
        likes = 0;
    }

    public void likePost()
    {
        likes++;
    }

    public int getLikes()
    {
        return likes;
    }

    public String getUser()
    {
        return user;
    }

    public void newPost(String inPost)
    {
        post = inPost;
    }

    public void addLiker(String liker)
    {
        likers.insertLast(liker);
    }

    public boolean checkLiked(String liker)
    {
        Iterator iter = likers.iterator();
        Object str;
        boolean liked = false;
        while(iter.hasNext())
        {
            str = iter.next();
            if(liker.equals(((String)str)))
            {
                liked = true;
            }
        }
        return liked;
    }

    public String getPost()
    {
        return post;
    }
}
