package java.com.wine.to.up.am.parser.service.service.impl;

import com.wine.to.up.am.parser.service.domain.entity.Wine;
import com.wine.to.up.am.parser.service.repository.*;
import com.wine.to.up.am.parser.service.service.impl.SearchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.com.wine.to.up.am.parser.service.service.impl.SampleObjects.getSampleWineList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author : bgubanov
 * @since : 13.10.2020
 **/
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SearchServiceImplTest {

    private WineRepository wineRepositoryMock = Mockito.mock(WineRepository.class);

    @InjectMocks
    SearchServiceImpl searchService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllLessByRub() {
        List<Wine> wines = getSampleWineList();
        when(wineRepositoryMock.findAllByPriceLessThan(5d)).thenReturn(wines);
        when(wineRepositoryMock.findAllByPriceLessThan(-5d)).thenReturn(new ArrayList<>());
        var listWines = searchService.findAllLessByRub(5d);
        assertEquals(1, listWines.size());
        var wine = listWines.get(0);
        assertEquals("wine", wine.getName());
        assertEquals("http", wine.getPicture());
        assertEquals("color", wine.getColor());
        assertEquals("sugar", wine.getSugar());
        assertEquals("country", wine.getCountry());
        assertEquals(1, wine.getGrapes().size());
        assertEquals("grape", wine.getGrapes().get(0));
        assertEquals((Double) 0.0, wine.getAlco());
        assertEquals((Double) 0.0, wine.getValue());
        var listWinesEmpty = searchService.findAllLessByRub(-5d);
        assertEquals(0, listWinesEmpty.size());
    }
}