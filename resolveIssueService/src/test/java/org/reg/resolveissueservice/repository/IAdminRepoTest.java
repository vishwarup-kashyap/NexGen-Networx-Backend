package org.reg.resolveissueservice.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.reg.resolveissueservice.entity.Admin;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class IAdminRepoTest {

    @Mock
    private IAdminRepo adminRepository;

    @Test
    public void testSaveAdmin() {
        // Given
        Admin adminVishu = new Admin(1L, "vishu", "vishu@example.com");
        Admin adminAryan = new Admin(1L, "aryan", "aryan@example.com");

        when(adminRepository.save(adminVishu)).thenReturn(adminVishu);

        // When
        Admin savedAdmin = adminRepository.save(adminVishu);

        // Then
        assertThat(savedAdmin).isNotNull();
        assertThat(savedAdmin.getName()).isEqualTo("vishu");
        assertThat(savedAdmin.getEmail()).isEqualTo("vishu@example.com");
    }

    @Test
    public void testFindAdminById() {
        // Given
        Admin adminAryan = new Admin(1L, "aryan", "aryan@example.com");
        Long adminId = adminAryan.getId();
        when(adminRepository.findById(adminId)).thenReturn(Optional.of(adminAryan));

        // When
        Optional<Admin> foundAdmin = adminRepository.findById(adminId);

        // Then
        assertThat(foundAdmin).isPresent();
        assertThat(foundAdmin.get().getName()).isEqualTo("aryan");
    }

    @Test
    public void testFindAllAdmins() {
        // Given
        when(adminRepository.findAll()).thenReturn(List.of(new Admin(1L, "rewa", "rewa@example.com"),
                new Admin(2L, "sharon", "sharon@example.com")));

        // When
        List<Admin> admins = adminRepository.findAll();

        // Then
        assertThat(admins).hasSize(2);
        assertThat(admins.get(0).getName()).isEqualTo("rewa");
        assertThat(admins.get(1).getName()).isEqualTo("sharon");
    }

    @Test
    public void testUpdateAdmin() {
        // Given
        Admin adminVishu = new Admin(1L, "vishu", "vishu@example.com");
        when(adminRepository.save(adminVishu)).thenReturn(adminVishu);

        // When
        Admin updatedAdmin = adminRepository.save(adminVishu);

        // Then
        assertThat(updatedAdmin.getName()).isEqualTo("vishu");
    }

    @Test
    public void testDeleteAdmin() {
        // Given
        Admin adminAryan = new Admin(1L, "aryan", "aryan@example.com");
        Long adminId = adminAryan.getId();

        // When
        adminRepository.deleteById(adminId);

        // Then
        assertThat(adminRepository.findById(adminId)).isEmpty();
    }
}
