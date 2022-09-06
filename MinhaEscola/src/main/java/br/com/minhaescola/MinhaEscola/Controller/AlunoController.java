package br.com.minhaescola.MinhaEscola.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.minhaescola.MinhaEscola.Repository.AlunoRepository;
import br.com.minhaescola.MinhaEscola.dto.RequisicaoNovoAluno;
import br.com.minhaescola.MinhaEscola.model.Aluno;

@Controller
@RequestMapping("aluno")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoAluno requisicao) {		
		return "aluno/formulario";
	}

	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoAluno requisicao, BindingResult result) {
		if(result.hasErrors()) {
			return "aluno/formulario";
		}
		
		Aluno aluno = requisicao.toAluno();
		alunoRepository.save(aluno);
		return "redirect:/home";
	}

}
