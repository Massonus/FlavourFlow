package com.massonus.rccnavigator.service;

import com.massonus.rccnavigator.dto.CheckDto;
import com.massonus.rccnavigator.dto.CompanyDto;
import com.massonus.rccnavigator.dto.ItemType;
import com.massonus.rccnavigator.entity.Company;
import com.massonus.rccnavigator.entity.CompanyCountry;
import com.massonus.rccnavigator.entity.KitchenCategory;
import com.massonus.rccnavigator.entity.PriceCategory;
import com.massonus.rccnavigator.repo.CompanyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @Mock
    private CompanyRepo companyRepo;
    @Mock
    private CompanyCountryService countryService;
    @Mock
    private KitchenCategoryService categoryService;
    @InjectMocks
    private CompanyService target;

    private CompanyDto companyDto;
    private CheckDto checkDto;
    private Company company;
    private KitchenCategory category;
    private CompanyCountry country;

    @BeforeEach
    void setUp() {
        checkDto = new CheckDto();
        checkDto.setCheckId(1L);
        checkDto.setNewId(1L);

        companyDto = new CompanyDto();
        companyDto.setCompanyId(1L);
        companyDto.setTitle("first");
        companyDto.setCountryId(1L);
        companyDto.setCategoryId(1L);
        companyDto.setPriceCategory(PriceCategory.MEDIUM);
        companyDto.setImageLink("");

        company = new Company();
        company.setTitle("PNS");

        category = new KitchenCategory();
        category.setId(1L);

        country = new CompanyCountry();
        country.setId(1L);
    }

    @Test
    void shouldSaveCompany() {
        when(categoryService.getCategoryById(companyDto.getCategoryId())).thenReturn(category);
        when(countryService.getCountryById(companyDto.getCountryId())).thenReturn(country);

        target.saveCompany(companyDto);

        ArgumentCaptor<Company> companyCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepo, times(1)).save(companyCaptor.capture());

        Company savedCompany = companyCaptor.getValue();
        assertEquals(savedCompany.getTitle(), companyDto.getTitle());
    }

    @Test
    void shouldEditCompany() {
        when(companyRepo.findCompanyById(companyDto.getCompanyId())).thenReturn(company);
        when(categoryService.getCategoryById(companyDto.getCategoryId())).thenReturn(category);
        when(countryService.getCountryById(companyDto.getCountryId())).thenReturn(country);

        target.editCompany(companyDto);

        ArgumentCaptor<Company> companyCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepo, times(1)).save(companyCaptor.capture());

        Company savedCompany = companyCaptor.getValue();
        assertEquals(savedCompany.getTitle(), companyDto.getTitle());
        assertSame(savedCompany.getCompanyCountry(), country);
        assertSame(savedCompany.getKitchenCategory(), category);
    }

    @Test
    void shouldMoveCompaniesToAnotherCountry() {
        CheckDto responseCheckDto = target.moveCompaniesToAnotherCountry(checkDto);
        assertSame(responseCheckDto.getItemType(), ItemType.COMPANYCOUNTRY);
    }

    @Test
    void shouldMoveCompaniesToAnotherCategory() {
        CheckDto responseCheckDto = target.moveCompaniesToAnotherCategory(checkDto);
        assertSame(responseCheckDto.getItemType(), ItemType.KITCHENCATEGORY);
    }
}