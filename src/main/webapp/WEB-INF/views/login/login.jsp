<%-- 
    Document   : login
    Created on : 02/08/2018, 15:23:46
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de marcação de consulta</title>
    </head>
    <body style="font-family: arial; background: rgba(255, 0, 0, 0.5); text-align: center;">
        <!--div style="text-align: center; margin-top: 2em; margin-bottom: 2em;">
            <img src="${pageContext.request.contextPath}/resources/img/logo.png" width="350px">
        </div-->
        <div style="background: white; border-radius: 10px; zoom: 170%; width: auto; min-width: 10rem; display: inline-block; height: auto;             
             text-align: center; position: relative; overflow: auto; padding: 2em;">
            <div style="padding-top: 2rem;">
                <h1>Sistema de marcação de consulta</h1>
                <form action="autentica" method="post">
                    <text style="display: inline-block; width: 60px; text-align: right;">Usuário: </text>
                    <input type="text" style="width: 100px;" name="userName">
                    <br>
                    <text style="display: inline-block; width: 60px; text-align: right;">Senha: </text>
                    <input type="password" style="width: 100px" name="userPassword">
                    <br><br>
                    <input type="submit" value="Entrar" style="display: inline-block; width: auto; border-radius: 2px; min-width: 20%;">
                </form>            
            </div>            
        </div>

    </body>
</html>
