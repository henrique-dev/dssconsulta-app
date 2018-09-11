<%-- 
    Document   : profile-medic-data
    Created on : 31/08/2018, 20:05:19
    Author     : Paulo Henrique Gonçalves Bacelar
--%>
<%@page contentType="application/json"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
(list:
[medicProfile:
    {id:${medicProfile.id}},
    {name:${medicProfile.medicName}},
    {crm:${medicProfile.medicCrm}},
    {expAge:${medicProfile.medicProfileExpAge}},
    {evaluationAvg:${medicProfile.medicProfileEvaluationAvg}},
    {evaluationCount:${medicProfile.medicProfileEvaluationCount}},
    {infoCompl:${medicProfile.medicProfileInfoCompl}},
    {bio:${medicProfile.medicProfileBio}}
    <c:if test="${medicProfile.file != null}">
        ,
        {imageId:${medicProfile.file.fileId}},
        {imageLength:${medicProfile.file.fileLength}}
    </c:if>
])