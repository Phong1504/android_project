package API;

import com.example.mucsicapp.Model.Album;
import com.example.mucsicapp.Model.Baihat;
import com.example.mucsicapp.Model.ChuDe;
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
    @GET("baihatquangcao.php")
    Call<List<QuangCao>> GetDataBanner();
    @GET("chudetheloai.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET ("Album.php")
    Call<List<Album>> GetAlbum();
    @GET("chudetheloai.php")
    Call<List<TheLoai>> GetChude();

    @GET ("baihatyeuthich.php")
    Call<List<Baihat>> GetBaihatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDsbaihatquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetDanhsachtheotheloai(@Field("idtheloai") String idtheloai);

    @GET ("danhsachchude.php")
    Call<List<ChuDe>> getAllChude();

    @FormUrlEncoded
    @POST("theloaiTheochude.php")
    Call<List<TheLoai>> GetTheloaiTheoChude(@Field("idchude") String idchude);

    @GET ("tatcachude.php")
    Call<List<Album>> GetALlAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetAlbumListBaihat(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateLuuotthich.php")
    Call<String> Updateluotthich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);
}
