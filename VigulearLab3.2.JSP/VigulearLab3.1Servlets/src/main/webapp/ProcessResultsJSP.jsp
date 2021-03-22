<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
    <title>Answers from <c:out value="${param['firstName']}"/> <c:out value="${param['familyName']}"/></title>
    <link rel=icon href="icon.png" type="image/png"/>
</head>
<body>
<h1>Thank you for taking this test!</h1><br/>
<h2>Answers for <b><c:out value="${param.firstName}"/> <c:out value="${param.familyName}"/></b></h2>
1. Perpetual:<br/><c:out value="${param.question1}"/><br/><br/>
2. In a nutshell:<br/><c:out value="${param.question2}"/><br/><br/>
3. Unutterable:<br/><c:out value="${param.question3}"/><br/><br/>
4. Ambiguous:<br/><c:out value="${param.question4}"/><br/><br/>
5. Scrumptious:<br/><c:out value="${param['question5']}"/><br/><br/>
6. Ample:<br/><c:out value="${param.question6}"/><br/><br/>
7. Preposterous:<br/>
<c:set var = "answers7" scope="session" value="${param['question7']}"/>
<c:if test = "${not empty answers7}">
    <c:forEach var="answer" items="${paramValues.question7}">
        <c:out value = "${answer}"/><br/>
    </c:forEach>
</c:if>
<c:if test = "${empty answers7}">
    No Answer<br/>
</c:if>
<br/><br/>
8. Picked in map:<br/><c:out value="${param.pictureId}"/><br/><br/>
9. Have a bone to pick:<br/>
<c:set var = "answers9" scope="session" value="${param['question9']}"/>
<c:choose>
    <c:when test="${not empty answers9}">
        <c:out value = "${answers9}"/><br/><br/>
    </c:when>
    <c:otherwise>
        No Answer<br/><br/>
    </c:otherwise>
</c:choose>

<%--Acquire parametres from request--%>
<c:set var="firstName">${param.firstName}</c:set>
<c:set var="familyName">${param.familyName}</c:set>
<c:set var="question1">${param.question1}</c:set>
<c:set var="question2">${param.question2}</c:set>
<c:set var="question3">${param.question3}</c:set>
<c:set var="question4">${param.question4}</c:set>
<c:set var="question5">${param.question5}</c:set>
<c:set var="question6">${param.question6}</c:set>
<c:set var="question7"/>
<c:forEach var = "subString" items="${paramValues.question7}" varStatus="stat">
    <c:set var = "question7" value="${stat.first ? '' : question7} ${subString}"/>
</c:forEach>
<c:set var="question8">${param.pictureId}</c:set>
<c:set var="question9">${param.question9}</c:set>

<c:catch var = "exception">
<sql:setDataSource var = "dataBase" driver="com.mysql.jdbc.Driver" url = "jdbc:mysql://localhost:3306/englishtest" user = "root" password="root"/>
<sql:update dataSource = "${dataBase}" var = "update">
    insert into answers(firstName, familyName, Perpetual, InANutshell, Unutterable, Ambiguous, Scrumptious, Ample, Preposterous, PickedInMap, HaveABoneToPick)
    values('${firstName}', '${familyName}', '${question1}', '${question2}', '${question3}', '${question4}', '${question5}', '${question6}', '${question7}', '${question8}', '${question9}')
</sql:update>
</c:catch>
<c:choose>
    <c:when test="${exception != null}">
        <b>Error saving answers on server!</b><br/>
        <b>You must have already taken this test before...</b>
    </c:when>
    <c:otherwise>
        <b>Your answers were successfully saved in data base!</b>
    </c:otherwise>
</c:choose>
</body>
</html>