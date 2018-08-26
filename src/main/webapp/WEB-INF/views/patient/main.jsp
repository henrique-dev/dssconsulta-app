<%-- 
    Document   : main
    Created on : 03/08/2018, 14:21:31
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de marcação de consulta</title>
    </head>
    <body style="font-family: arial; background: rgba(255, 0, 0, 0.5);">
        <div style="text-align: center; margin-top: 2em; margin-bottom: 2em;">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" width="350px">
        </div>
        <div style="background: white; border-radius: 10px; zoom: 170%; width: auto; min-width: 20em; max-width: 50em; height: auto; min-height: 15em; max-height: 40em;
             margin: auto; text-align: center; position: relative;">            
            <div style="padding-top: 2rem; padding-bottom: 2rem; height: auto;">
                <h1>Sistema de marcação de consulta</h1>    
                <!--form action="" method="">                    
                    <input type="submit" value="Suporte online" style="display: inline-block; width: 40%; height: 50px; border-radius: 2px;"><br>
                    <input type="submit" value="Historico de agendamentos" style="display: inline-block; width: 40%; height: 50px; border-radius: 2px;"><br>
                    <input type="submit" value="Buscar médicos e clínicas" style="display: inline-block; width: 40%; height: 50px; border-radius: 2px;">
                </form-->                            
                <h3>Teste de requisições</h3>
                <a href="Paciente/MeuPerfil?patientId=0">Meu perfil</a><br>
                <a href="Paciente/MinhaAgenda?patientId=0">Minha agenda</a><br>
                <a href="Paciente/MinhaAgenda/MinhaConsulta?consultId=0">Minha consulta</a><br>
                <a href="Paciente/ListarEspecialidades">Listar especialidades</a><br>                
                <a href="Paciente/ListarMedicos?specialityId=0">Listar medicos com especialidade 0</a><br>
                <a href="Paciente/PerfilUnidadeSaude?clinicId=0">Perfil da clinica 0</a><br>
                <a href="Paciente/AgendarConsulta?patientId=0&medicId=0&specialityId=2&medicWorkAddressId=0">Agendar Consulta</a><br>
                <a href="Paciente/MinhasAvaliacoes?patientId=0">Minhas avaliações</a><br>
            </div>            
        </div>

    </body>
</html>

