<%-- 
    Document   : account-data
    Created on : 05/09/2018, 16:49:29
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(accountSpecialityList:
<c:forEach items="${accountSpecialityList}" var="speciality" varStatus="i">
    [consult:
        {specialityName:${speciality}}        
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)