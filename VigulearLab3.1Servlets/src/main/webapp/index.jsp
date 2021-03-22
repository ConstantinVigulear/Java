<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Custom English Test </title>
    <link rel = icon href="icon.png" type="image/png">
</head>
<body>
<table width=500>
    <tr>
        <td>
            <img src = "icon.png" width="128" height="128" alt="Flag">
            <h3> Custom English Vocabulary Test </h3>
            <FORM action="VigulearLab3Servlets.ServletToJSP" method="post">

                <!User Credentials>
                <fieldset WIDTH=400>
                    <legend> User: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="text" NAME="firstName" placeholder="firstName" required>
                    </label><BR><BR>
                    <label>
                        <INPUT TYPE="text" NAME="familyName" placeholder="familyName" required>
                    </label> <BR>
                </fieldset>
                <br>

                <!Question1 Radio>
                <fieldset WIDTH=400>
                    <legend><b>1.</b> Perpetual: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question1" VALUE="Eternal">
                    </label> Eternal.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question1" VALUE="Nice">
                    </label> Nice.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question1" VALUE="Tasty">
                    </label> Tasty.<BR>
                </fieldset>
                <br>

                <!Question2 Radio>
                <fieldset WIDTH=400>
                    <legend><b>2.</b> In a nutshell: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question2" VALUE="God bless you!">
                    </label> God bless you!<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question2" VALUE="It is a long story">
                    </label> It is a long story.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question2" VALUE="Something of small size, amount, or scope">
                    </label> Something of small size, amount, or scope.<BR>
                </fieldset>
                <br>

                <!Question3 Radio>
                <fieldset WIDTH=400>
                    <legend><b>3.</b> Unutterable: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question3" VALUE="Indescribable">
                    </label> Indescribable.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question3" VALUE="Awful">
                    </label> Awful.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question3" VALUE="Mediocre">
                    </label> Mediocre.<BR>
                </fieldset>
                <br>

                <!Question4 Radio>
                <fieldset WIDTH=400>
                    <legend><b>4.</b> Ambiguous: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question4" VALUE="Plain">
                    </label> Plain.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question4" VALUE="Paramount">
                    </label> Paramount.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question4" VALUE="Equivocal">
                    </label> Equivocal.<BR>
                </fieldset>
                <br>

                <!Question5 Radio>
                <fieldset WIDTH=400>
                    <legend><b>5.</b> Scrumptious: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question5" VALUE="Tasty">
                    </label> Tasty.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question5" VALUE="Disgusting">
                    </label> Disgusting.<BR>
                    <label>
                        <INPUT TYPE="RADIO" NAME="question5" VALUE="Ambivalent">
                    </label> Ambivalent.<BR>
                </fieldset>
                <br>

                <!Question6 List>
                <fieldset WIDTH=400>
                    <legend><b>6.</b> Ample: &nbsp;</legend>
                    <label>
                        <select name = "question6">
                            <option value="Minimal">Minimal</option>
                            <option value="Tangled">Tangled</option>
                            <option value="Abundant">Abundant</option>
                            <option value="Corrupt">Corrupt</option>
                        </select>
                    </label>
                </fieldset>
                <br>

                <!Question7 Checkbox>
                <fieldset WIDTH=400>
                    <legend><b>7.</b> Preposterous: &nbsp;</legend>
                    <label>
                        <INPUT TYPE="CHECKBOX" NAME="question7" VALUE="Reasonable">
                    </label> Reasonable.<BR>
                    <label>
                        <INPUT TYPE="CHECKBOX" NAME="question7" VALUE="Insane">
                    </label> Insane.<BR>
                    <label>
                        <INPUT TYPE="CHECKBOX" NAME="question7" VALUE="Absurd">
                    </label> Absurd.<BR>
                    <label>
                        <INPUT TYPE="CHECKBOX" NAME="question7" VALUE="Hypocritical">
                    </label> Hypocritical.<BR>
                    <label>
                        <INPUT TYPE="CHECKBOX" NAME="question7" VALUE="Bizarre">
                    </label> Bizarre.<BR>
                </fieldset>
                <br>

                <!Question8 Map>
                <fieldset WIDTH=400>
                    <legend><b>8.</b> Click on what best corresponds the description given below: &nbsp;</legend>
                    <p><i>A device that can be instructed to carry out arbitrary sequences of arithmetic or logical
                        operations automatically</i></p>
                    <img src="workplace.jpg" alt="Workplace" usemap="#workmap" width="400" height="379">
                    <map name="workmap">
                        <area onclick="processInputValue('Computer');" shape="rect" coords="34,44,270,350"
                              alt="Computer" href="#">
                        <area onclick="processInputValue('Phone');" shape="rect" coords="290,172,333,250"
                              alt="Phone" href="#">
                        <area onclick="processInputValue('Cup of coffee');" shape="circle" coords="337,300,44"
                              alt="Cup of coffee" href="#">
                        <input id="pictureId" type="hidden" name="pictureId" value = "">
                        <script>
                            function processInputValue(param) {
                                // Selecting the input element and get its value
                                document.getElementById('pictureId').value = param;

                                // Displaying the value
                                alert("You have selected '" + param + "'");
                            }
                        </script>
                    </map>
                </fieldset>
                <br>

                <!Question9 TextArea>
                <fieldset WIDTH=400>
                    <legend><b>9.</b> Please type in your understanding of the following idiom: &nbsp;</legend>
                    <p><i>Have a bone to pick.</i></p>
                    <br>
                    <label>
                        <textarea rows="5" cols="60" name="question9" placeholder="Enter text here..."></textarea>
                    </label>
                    <br><br><br>

                    <INPUT TYPE="SUBMIT" VALUE="Test yourself">
                    <br><br>
                </fieldset>
            </FORM>
        </td>
    </tr>
</table>
</body>
</html>