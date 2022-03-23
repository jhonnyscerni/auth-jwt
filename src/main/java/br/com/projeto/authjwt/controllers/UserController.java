package br.com.projeto.authjwt.controllers;

import br.com.projeto.authjwt.dto.UserDto;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserDto> search(UserFilter filter,
        @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
        return userService.search(filter, pageable);
    }

    @GetMapping("/{usuarioId}")
    public UserDto buscar(@PathVariable Long usuarioId) {
        return userService.findByIdUserDto(usuarioId);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    public UsuarioModel adicionar(@RequestBody @Valid UsuarioInputComSenhaModel usuarioInput) {
//        Usuario usuario = usuarioService.salvar(disassembler.toDomainObject(usuarioInput));
//        return assembler.toModel(usuario);
//    }
//
//    @PutMapping("/{usuarioId}")
//    public UsuarioModel atualizar(@PathVariable Long usuarioId,
//        @RequestBody @Valid UsuarioInputComSenhaModel usuarioInput) {
//        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
//
//        if (usuarioInput.getSenha() != null || usuarioInput.getSenha().isEmpty()){
//            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioInput.getSenha());
//            usuarioInput.setSenha(senhaCriptografada);
//        }
//        usuarioInput.setAtivado(usuario.isAtivado());
//
//        Usuario usuarioAlterado = disassembler.toDomainObjectComSenha(usuarioInput);
//        usuarioAlterado.setDataCadastro(usuario.getDataCadastro());
//        usuarioAlterado = usuarioService.salvar(usuarioAlterado);
//        return assembler.toModel(usuarioAlterado);
//    }

//    @DeleteMapping("/{usuarioId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void remover(@PathVariable Long usuarioId) {
//        usuarioService.excluir(usuarioId);
//    }

}
