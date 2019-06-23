<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/">
      <html>
        <body>
            <h1>Reservation report</h1>
            <table border="1">
               <tr>
                  <th>Start</th>
                  <th>End</th>
                  <th>Price</th>
                  <th>Status</th>
               </tr>
               <xsl:for-each select="RezervacijaKorisnika">
                  <tr>
                     <td>
                        <xsl:value-of select="datumPocetka" />
                     </td>
                     <td>
                        <xsl:value-of select="datumZavrsetka" />
                     </td>
                     <td>
                        <xsl:value-of select="cenaSmestaja" />
                        $
                     </td>
                     <td>
                        <xsl:value-of select="stanje" />
                     </td>
                  </tr>
               </xsl:for-each>
            </table>
         </body>
      </html>
   </xsl:template>
</xsl:stylesheet>