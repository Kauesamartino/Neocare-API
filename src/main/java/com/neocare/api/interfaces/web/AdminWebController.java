package com.neocare.api.interfaces.web;

import com.neocare.api.application.usecase.usuario.DesativarUsuarioUseCase;
import com.neocare.api.application.usecase.usuario.LocalizarTodosOsUsuariosUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminWebController {

    private final LocalizarTodosOsUsuariosUseCase localizarTodosOsUsuarios;
    private final DesativarUsuarioUseCase desativarUsuarioUseCase;

    public AdminWebController(LocalizarTodosOsUsuariosUseCase localizarTodosOsUsuarios,
                              DesativarUsuarioUseCase desativarUsuarioUseCase) {
        this.localizarTodosOsUsuarios = localizarTodosOsUsuarios;
        this.desativarUsuarioUseCase = desativarUsuarioUseCase;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", localizarTodosOsUsuarios.execute());
        return "admin/usuarios";
    }

    @PostMapping("/usuarios/{cpf}/desativar")
    public String desativarUsuario(@PathVariable String cpf, RedirectAttributes redirectAttributes) {
        try {
            desativarUsuarioUseCase.execute(cpf);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário desativado com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao desativar usuário: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }
}
