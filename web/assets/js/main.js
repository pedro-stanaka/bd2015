function mostraErro() {
    if ($('#erro').length > 0) {
        alert($('#erro').text());
    }
}

$(document).ready(function() {
    mostraErro();
});

function visualizaUsuario(url) {
    $.get(url, function(data) {
        var usuario = JSON.parse(data);
        $('#modal_id').html('<strong>ID: </strong>' + usuario.id);
        $('#modal_login').html('<strong>Login: </strong>' + usuario.login);
        $('#modal_nome').html('<strong>Nome: </strong>' + usuario.nome);
        $('#modal_nascimento').html('<strong>Data de nascimento: </strong>' + usuario.nascimento);
        $('#modal_visualizar_usuario').modal();
    });
}

function excluiUsuario(url) {
    $('#link_excluir_usuario').attr('href', url);
    $('#modal_excluir_usuario').modal();
}

function excluiUsuarios() {
    $('#form_excluir_usuarios').submit();
}