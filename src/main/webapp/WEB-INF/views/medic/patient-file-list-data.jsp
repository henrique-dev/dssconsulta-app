<%-- 
    Document   : patient-file-list-data
    Created on : 11/09/2018, 23:52:49
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
(medicWorkAddressList:
<c:forEach items="${fileList}" var="file" varStatus="i">
    [file:
        {fileId:${file.fileId}},
        {fileLength:${file.fileLength}},
        {fileType:${file.type}}        
    ]
    <c:if test="${!i.last}">
        ,
    </c:if>
</c:forEach>) 