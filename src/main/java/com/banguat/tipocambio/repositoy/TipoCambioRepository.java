package com.banguat.tipocambio.repositoy;

import com.banguat.tipocambio.model.TipoCambioRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambioRegistro, Long> {
    Optional<TipoCambioRegistro> findTopByOrderByFechaConsultaDesc();
}
