package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Invoice_Detail;

public interface Invoice_DetailRepository extends JpaRepository<Invoice_Detail, Long> {

}
