package com.meimei.meimusic.http.api;

import com.meimei.meimusic.entity.Individuality;
import com.meimei.meimusic.entity.RankingList;
import com.meimei.meimusic.entity.Song;
import com.meimei.meimusic.entity.SongList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by 梅梅 on 2017/3/22.
 */
public interface Api {

    /**
     * 个性推荐里的新歌、推荐歌单、电台
     * @return
     */
    @Headers("user-agent:Mozilla / 5.0(Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET("v1/restserver/ting?from=android&version=5.8.1.0&channel=ppzs&operator=3&method=baidu.ting.plaza.index&cuid=89CF1E1A06826F9AB95A34DC0F6AAA14")
    Observable<Individuality> getIndividuality();

    /**
     * 歌单
     * @param pageSize  每页数量
     * @param pageNo    页码
     * @return
     */
    @Headers("user-agent:Mozilla / 5.0(Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET("v1/restserver/ting?from=android&version=5.6.5.6&format=json&method=baidu.ting.diy.gedan") //baidu.ting.diy.getHotGeDanAndOfficial
    Observable<SongList> getSongs(@Query("page_size")int pageSize,
                                  @Query("page_no")int pageNo);

    @Headers("user-agent:Mozilla / 5.0(Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET("v1/restserver/ting?from=android&version=5.6.5.6&format=json&method=baidu.ting.billboard.billList")
    Observable<RankingList> getRankingList(@Query("type")int type,
                                           @Query("offset")int offset,
                                           @Query("size")int size,
                                           @Query("fields")String fields);

    @Headers("user-agent:Mozilla / 5.0(Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET("v1/restserver/ting?from=android&version=5.6.5.6&format=json&method=baidu.ting.song.getInfos")
    Observable<Song> getSongInfo(@Query("songid")String songid,
                                 @Query("ts")String currentTimeMillism,
                                 @Header("e") String e);

}
