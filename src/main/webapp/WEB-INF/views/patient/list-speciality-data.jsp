<%-- 
    Document   : list-speciality-data
    Created on : 31/08/2018, 15:35:32
    Author     : Paulo Henrique Gonçalves Bacelar
--%>

<%@page contentType="text/html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(specialityList:<c:forEach items="${specialityList}" var="speciality" varStatus="i">
    [speciality:
        {id:${speciality.specialityId}},
        {name:${speciality.specialityName}}        
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)
