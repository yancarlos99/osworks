package br.com.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.osworks.domain.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
