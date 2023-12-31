package API;

import com.example.mucsicapp.Model.Album;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.Model.QuangCao;
import com.example.mucsicapp.Model.TheLoai;
import com.example.mucsicapp.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("example22.php")
    Call<List<QuangCao>> GetDataBanner();
    @GET("chudetheloai.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET ("Album.php")
    Call<List<Album>> GetAlbum();
    @GET("chudetheloai.php")
    Call<List<TheLoai>> GetChude();

    @GET ("baihhatyeuthich.php")
    Call<List<Baihat>> GetBaihatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDsbaihatquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachtheotheloai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa)
}
