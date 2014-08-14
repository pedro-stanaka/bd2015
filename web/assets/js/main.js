function mostraErro() {
    if ($('#erro').length > 0) {
        alert($('#erro').text());
    }
}

function excluiUsuario() {
    $('#link_confirmacao_excluir_usuario').attr('href', $(this).data('href'));
    $('#modal_excluir_usuario').modal();
}

function excluiUsuarios() {
    $('#form_excluir_usuarios').submit();
}

function visualizaUsuario() {
    $.get($(this).data('href'), function(data) {
        var usuario = JSON.parse(data);
        $('#modal_id').html('<strong>ID: </strong>' + usuario.id);
        $('#modal_login').html('<strong>Login: </strong>' + usuario.login);
        $('#modal_nome').html('<strong>Nome: </strong>' + usuario.nome);
        $('#modal_nascimento').html('<strong>Data de nascimento: </strong>' + usuario.nascimento);
        $('#modal_visualizar_usuario').modal();
    });
}

$(document).ready(function() {
    mostraErro();
    $(document).on('click', '.link_excluir_usuario', excluiUsuario);
    $(document).on('click', '.button_confirmacao_excluir_usuarios', excluiUsuarios);
    $(document).on('click', '.link_visualizar_usuario', visualizaUsuario);
});