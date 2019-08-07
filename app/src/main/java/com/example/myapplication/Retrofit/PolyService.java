package com.example.myapplication.Retrofit;

import com.example.myapplication.ModelCategory.Category;
import com.example.myapplication.ModelRetrofit.Post;
import com.example.myapplication.ModelRetrofit.WpFeaturedmedium_;

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

}
