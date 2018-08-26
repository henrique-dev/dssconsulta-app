<%-- 
    Document   : list-speciality
    Created on : 26/08/2018, 14:42:56
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>           
[(<c:forEach items="${specialityList}" var="speciality" varStatus="i">
    [{specialityId:${speciality.specialityId}},
    {specialityName:${speciality.specialityName}}]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>)]