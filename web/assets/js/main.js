function showDatepicker() {
    if ($('.datepicker').length > 0) {
        $('.datepicker').datepicker({
            language: 'pt-BR',
            format: 'dd/mm/yyyy',
            autoclose: true
        });
    }
}

function showErrors() {
    if ($('.error-message').length > 0) {
        $('.error-message').alert();
    }

    if ($('.rollback-error-message').length > 0) {
        $('.rollback-error-message').alert();
    }
}

function deleteUser() {
    $('.link_confirmacao_excluir_usuario').attr('href', $(this).data('href'));
    $('.modal_excluir_usuario').modal();
}

function deleteUsers(e) {
    e.preventDefault();
    $('.form_excluir_usuarios').submit();
}

function readUser(e) {
    e.preventDefault();
    $.get($(this).data('href'), function (data) {
        var usuario = JSON.parse(data);
        $('.p_id').html('<strong>ID: </strong>' + usuario.id);
        $('.p_login').html('<strong>Login: </strong>' + usuario.login);
        $('.p_nome').html('<strong>Nome: </strong>' + usuario.nome);
        $('.p_nascimento').html('<strong>Data de nascimento: </strong>' + usuario.nascimento);
        $('.modal_visualizar_usuario').modal();
    });
}


$(document).on('focusout', '#usuario-login', function (e) {
    var $input = $(this);
    $.post($.url("/usuario/checkLogin"), { login: $("#usuario-login").val() }, function(data) {
        var $formGroup = $input.parents(".form-group").first();
        if (data.status == "USADO") {
            if (!$formGroup.hasClass("has-error")) {
                $formGroup.addClass("has-error");
            }
            $input.next("p").html("O login escolhido existe. Por favor, tente outro.");
        } else {
            if ($formGroup.hasClass("has-error")) {
                $formGroup.removeClass("has-error");
            }
            $input.next("p").html("");
        }
    });
});

$(document).ready(function () {
    showDatepicker();
    showErrors();
    $(document).on('click', '.link_excluir_usuario', deleteUser);
    $(document).on('click', '.button_confirmacao_excluir_usuarios', deleteUsers);
    $(document).on('click', '.link_visualizar_usuario', readUser);
});