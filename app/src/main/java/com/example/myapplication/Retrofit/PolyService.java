package com.example.myapplication.Retrofit;

import com.example.myapplication.ModelCategory.Category;
import com.example.myapplication.ModelMediaOfPost.MediaOfPost;
import com.example.myapplication.ModelPostOfCate.PostOfCate;
import com.example.myapplication.ModelEmbed.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PolyService {
//http://asian.dotplays.com/wp-json/wp/v2/posts?_embed

    @GET("wp-json/wp/v2/posts?_embed")
    Call<List<Post>> getSourceUrl();

    @GET("wp-json/wp/v2/categories/")
    Call<List<Category>> getCategories(@Query("page") int page,
                                       @Query("per_page") int per_page);


//http://asian.dotplays.com/wp-json/wp/v2/posts?categories=26&per_page=5&paging=1
    @GET("wp-json/wp/v2/posts/")
    Call<List<PostOfCate>> getPostOfCate(@Query("categories") int categories,
                                         @Query("per_page") int per_page,
                                         @Query("paging") int paging);
//    http://asian.dotplays.com/wp-json/wp/v2/media?parent=524
    @GET("wp-json/wp/v2/media/")
    Call<List<MediaOfPost>> getMediaOfPost(@Query("parent") int id);


}
