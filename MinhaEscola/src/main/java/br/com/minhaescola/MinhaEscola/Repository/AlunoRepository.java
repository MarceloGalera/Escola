package br.com.minhaescola.MinhaEscola.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhaescola.MinhaEscola.model.Aluno;
import br.com.minhaescola.MinhaEscola.model.Ano;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	List<Aluno> findByAno(Ano ano, Pageable sort);

}
