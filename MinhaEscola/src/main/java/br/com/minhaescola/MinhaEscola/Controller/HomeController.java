package br.com.minhaescola.MinhaEscola.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.minhaescola.MinhaEscola.Repository.AlunoRepository;
import br.com.minhaescola.MinhaEscola.model.Aluno;
import br.com.minhaescola.MinhaEscola.model.Ano;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	public String home(Model model) {
		Sort sort = Sort.by("numero").ascending();
		List<Aluno> alunos = alunoRepository.findAll(sort);
		model.addAttribute("alunos", alunos);
		return "home";
	}
	
	@GetMapping("/{ano}")
	public String porAno(@PathVariable("ano") String ano, Model model) {
		
		Sort sort = Sort.by("nome").ascending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		List<Aluno> alunos = alunoRepository.findByAno(Ano.valueOf(ano.toUpperCase()), paginacao);
		model.addAttribute("alunos", alunos);
		model.addAttribute("ano", ano);
		return "home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
