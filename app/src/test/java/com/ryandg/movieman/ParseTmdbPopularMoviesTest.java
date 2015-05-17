package com.ryandg.movieman;

import android.test.suitebuilder.annotation.SmallTest;

import com.ryandg.tmdb.TmdbMovie;
import com.ryandg.tmdb.TmdbMovieParser;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Ryan De Gruyter on 16/05/2015.
 *
 */
@SmallTest
public class ParseTmdbPopularMoviesTest {

    private String mockPopularMovies;

    @Before
    public void setupSampleJson() {
        mockPopularMovies = "{\"page\":1,\"results\":[{\"adult\":false,\"backdrop_path\":\"/rFtsE7Lhlc2jRWF7SRAU0fvrveQ.jpg\",\"id\":99861,\"original_title\":\"Avengers: Age of Ultron\",\"release_date\":\"2015-05-01\",\"poster_path\":\"/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg\",\"popularity\":37.23624,\"title\":\"Avengers: Age of Ultron\",\"video\":false,\"vote_average\":8.1,\"vote_count\":777},{\"adult\":false,\"backdrop_path\":\"/4liSXBZZdURI0c1Id1zLJo6Z3Gu.jpg\",\"id\":76757,\"original_title\":\"Jupiter Ascending\",\"release_date\":\"2015-02-27\",\"poster_path\":\"/aMEsvTUklw0uZ3gk3Q6lAj6302a.jpg\",\"popularity\":34.290607,\"title\":\"Jupiter Ascending\",\"video\":false,\"vote_average\":5.6,\"vote_count\":510},{\"adult\":false,\"backdrop_path\":\"/jxPeRkfOoWs6gFybOa8C4xrHLrm.jpg\",\"id\":76341,\"original_title\":\"Mad Max: Fury Road\",\"release_date\":\"2015-05-15\",\"poster_path\":\"/oQoIl0j4Lk6NFvOA0u7UREF8Sxm.jpg\",\"popularity\":24.98072,\"title\":\"Mad Max: Fury Road\",\"video\":false,\"vote_average\":8.0,\"vote_count\":115},{\"adult\":false,\"backdrop_path\":\"/c1OSRvorPXvGtFka7mgV6Jcw6jd.jpg\",\"id\":168259,\"original_title\":\"Furious 7\",\"release_date\":\"2015-04-03\",\"poster_path\":\"/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg\",\"popularity\":23.740533,\"title\":\"Furious 7\",\"video\":false,\"vote_average\":7.6,\"vote_count\":660},{\"adult\":false,\"backdrop_path\":\"/qhH3GyIfAnGv1pjdV3mw03qAilg.jpg\",\"id\":122917,\"original_title\":\"The Hobbit: The Battle of the Five Armies\",\"release_date\":\"2014-12-17\",\"poster_path\":\"/qrFwjJ5nvFnpBCmXLI4YoeHJNBH.jpg\",\"popularity\":17.984654,\"title\":\"The Hobbit: The Battle of the Five Armies\",\"video\":false,\"vote_average\":7.3,\"vote_count\":1323},{\"adult\":false,\"backdrop_path\":\"/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\"id\":157336,\"original_title\":\"Interstellar\",\"release_date\":\"2014-11-05\",\"poster_path\":\"/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\"popularity\":19.512549,\"title\":\"Interstellar\",\"video\":false,\"vote_average\":8.4,\"vote_count\":2164},{\"adult\":false,\"backdrop_path\":\"/razvUuLkF7CX4XsLyj02ksC0ayy.jpg\",\"id\":260346,\"original_title\":\"Taken 3\",\"release_date\":\"2014-12-16\",\"poster_path\":\"/c2SSjUVYawDUnQ92bmTqsZsPEiB.jpg\",\"popularity\":18.61194,\"title\":\"Taken 3\",\"video\":false,\"vote_average\":6.2,\"vote_count\":577},{\"adult\":false,\"backdrop_path\":\"/2BXd0t9JdVqCp9sKf6kzMkr7QjB.jpg\",\"id\":177572,\"original_title\":\"Big Hero 6\",\"release_date\":\"2014-11-07\",\"poster_path\":\"/3zQvuSAUdC3mrx9vnSEpkFX0968.jpg\",\"popularity\":15.297125,\"title\":\"Big Hero 6\",\"video\":false,\"vote_average\":8.0,\"vote_count\":1237},{\"adult\":false,\"backdrop_path\":\"/yaps1kzbHVxTpCmuMgWm8zaTdnN.jpg\",\"id\":9659,\"original_title\":\"Mad Max\",\"release_date\":\"1979-04-12\",\"poster_path\":\"/mfmJ0BftkGV5uRkVEsTWM39LRIt.jpg\",\"popularity\":15.417607,\"title\":\"Mad Max\",\"video\":false,\"vote_average\":6.5,\"vote_count\":257},{\"adult\":false,\"backdrop_path\":\"/hctIo3ugW79kzV6F9d6A1sMIAyP.jpg\",\"id\":205596,\"original_title\":\"The Imitation Game\",\"release_date\":\"2014-11-14\",\"poster_path\":\"/noUp0XOqIcmgefRnRZa1nhtRvWO.jpg\",\"popularity\":13.457163,\"title\":\"The Imitation Game\",\"video\":false,\"vote_average\":8.1,\"vote_count\":1045},{\"adult\":false,\"backdrop_path\":\"/hOOgtrByGgWfqGTTn5VL7jkLYXJ.jpg\",\"id\":147441,\"original_title\":\"Exodus: Gods and Kings\",\"release_date\":\"2014-12-12\",\"poster_path\":\"/f7SEuLjl2Ur2AX4YO5lxIP2IEVv.jpg\",\"popularity\":13.293708,\"title\":\"Exodus: Gods and Kings\",\"video\":false,\"vote_average\":5.7,\"vote_count\":532},{\"adult\":false,\"backdrop_path\":\"/OqCXGt5nl1cHPeotxCDvXLLe6p.jpg\",\"id\":98566,\"original_title\":\"Teenage Mutant Ninja Turtles\",\"release_date\":\"2014-08-08\",\"poster_path\":\"/oDL2ryJ0sV2bmjgshVgJb3qzvwp.jpg\",\"popularity\":13.194591,\"title\":\"Teenage Mutant Ninja Turtles\",\"video\":false,\"vote_average\":6.1,\"vote_count\":837},{\"adult\":false,\"backdrop_path\":\"/bHarw8xrmQeqf3t8HpuMY7zoK4x.jpg\",\"id\":118340,\"original_title\":\"Guardians of the Galaxy\",\"release_date\":\"2014-08-01\",\"poster_path\":\"/9gm3lL8JMTTmc3W4BmNMCuRLdL8.jpg\",\"popularity\":13.158483,\"title\":\"Guardians of the Galaxy\",\"video\":false,\"vote_average\":8.2,\"vote_count\":2488},{\"adult\":false,\"backdrop_path\":\"/fUn5I5f4069vwGFEEvA3HXt9xPP.jpg\",\"id\":131631,\"original_title\":\"The Hunger Games: Mockingjay - Part 1\",\"release_date\":\"2014-11-20\",\"poster_path\":\"/gj282Pniaa78ZJfbaixyLXnXEDI.jpg\",\"popularity\":13.118813,\"title\":\"The Hunger Games: Mockingjay - Part 1\",\"video\":false,\"vote_average\":7.0,\"vote_count\":1244},{\"adult\":false,\"backdrop_path\":\"/8o8HggaFadLBlLodUzvogBnV9vL.jpg\",\"id\":207703,\"original_title\":\"Kingsman: The Secret Service\",\"release_date\":\"2015-01-24\",\"poster_path\":\"/pXN5zQHdqmvpUZDPkLooGD6PnAW.jpg\",\"popularity\":13.005495,\"title\":\"Kingsman: The Secret Service\",\"video\":false,\"vote_average\":7.8,\"vote_count\":616},{\"adult\":false,\"backdrop_path\":\"/uCI4vNT4u9xOIU3pKV3qeaFcuO9.jpg\",\"id\":287424,\"original_title\":\"Maggie\",\"release_date\":\"2015-05-08\",\"poster_path\":\"/mFt7Oo3pf8f1BZAdWLyUpYM63aT.jpg\",\"popularity\":11.775191,\"title\":\"Maggie\",\"video\":false,\"vote_average\":5.4,\"vote_count\":32},{\"adult\":false,\"backdrop_path\":\"/9eb23pW5JdAfgK6vp4XzUwmNtR0.jpg\",\"id\":190859,\"original_title\":\"American Sniper\",\"release_date\":\"2014-12-25\",\"poster_path\":\"/ooy5M7QXEWVpOTAZIRGMskBQbQ9.jpg\",\"popularity\":12.740617,\"title\":\"American Sniper\",\"video\":false,\"vote_average\":7.5,\"vote_count\":952},{\"adult\":false,\"backdrop_path\":\"/czjU8NpPVTx5NzEk0zxe433xN9R.jpg\",\"id\":256591,\"original_title\":\"Focus\",\"release_date\":\"2015-02-27\",\"poster_path\":\"/9IElGiLkxPLUWZ3avy31bNSG3Tq.jpg\",\"popularity\":12.62791,\"title\":\"Focus\",\"video\":false,\"vote_average\":6.5,\"vote_count\":269},{\"adult\":false,\"backdrop_path\":\"/y5lG7TBpeOMG0jxAaTK0ghZSzBJ.jpg\",\"id\":198184,\"original_title\":\"Chappie\",\"release_date\":\"2015-03-06\",\"poster_path\":\"/nIQOgiHnAF9fnvqnOO0etd0YIb9.jpg\",\"popularity\":12.516993,\"title\":\"Chappie\",\"video\":false,\"vote_average\":6.9,\"vote_count\":230},{\"adult\":false,\"backdrop_path\":\"/iAX8lPlDEeGHatw79k1LfGLbTi2.jpg\",\"id\":68737,\"original_title\":\"Seventh Son\",\"release_date\":\"2015-02-06\",\"poster_path\":\"/A3SBRfCSgKNcW2IkbaaOgugLwRt.jpg\",\"popularity\":12.285536,\"title\":\"Seventh Son\",\"video\":false,\"vote_average\":5.4,\"vote_count\":204}],\"total_pages\":11150,\"total_results\":222999}";
    }
    @Test
    public void readPopularMovies() throws Exception {
        try {
            TmdbMovieParser tmdbMovieParser = new TmdbMovieParser();
            tmdbMovieParser.parse(mockPopularMovies);

            final ArrayList<TmdbMovie> movies = tmdbMovieParser.getMovies();
            assertEquals(movies.size(),20);
        } catch (IOException e) {
        }
    }
}
