package com.starwars.films.domain.usecase;

import com.starwars.films.data.repository.FilmsRepository;
import com.starwars.films.domain.model.Film;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetFilmImplTest {

    private static final String MOCK_URL = "http://swapi.co/api/films/1/";
    private static final String MOCK_ID = "1";

    @Mock
    private List<String> urls;

    @Mock
    private FilmsRepository filmsRepository;

    private GetFilm getFilm;

    @Test(expected = InvalidParameterException.class)
    public void must_throw_exception_on_null_url() {
        getFilm = new GetFilmImpl(filmsRepository);
        getFilm.run();
    }

    @Test
    public void successful_get_people() {
        when(filmsRepository.getFilm(MOCK_URL, MOCK_ID)).thenReturn(Observable.just(MockFilm()));
        TestObserver<Film> testObserver = new TestObserver<>();

        getFilm = new GetFilmImpl(filmsRepository);
        getFilm.setUrl(urls);
        getFilm.run().subscribe(testObserver);

        testObserver.assertNoValues();
        testObserver.assertNoErrors();
        testObserver.assertComplete();

    }

    private Film MockFilm() {
        return mock(Film.class);
    }

}
