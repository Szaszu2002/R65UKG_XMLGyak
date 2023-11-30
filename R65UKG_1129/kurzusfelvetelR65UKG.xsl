<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2> Szabó Alexandra Marianna Kurzusfelvétel - 2023/24. I. félév</h2>
                <table border="1">
                    <tr bgcolor="#b284be">
                        <th>ID</th>
                        <th>Jóváhagyás</th>
                        <th>Kurzus neve</th>
                        <th>Kredit</th>
                        <th>Hely</th>
                        <th>Időpont</th> 
                        <th>Oktató</th>
                    </tr>

                    <xsl:for-each select="kurzusfelvetelR65UKG/kurzus">
                        <tr>
                            <td><xsl:value-of select = "@id"/></td>
                            <td><xsl:value-of select = "@jovahagyas"/></td>
                            <td><xsl:value-of select = "kurzusneve"/></td>
                            <td><xsl:value-of select = "kredit"/></td>
                            <td><xsl:value-of select = "hely"/></td>
                            <td><xsl:value-of select = "idopont"/></td>
                            <td><xsl:value-of select = "oktato"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
