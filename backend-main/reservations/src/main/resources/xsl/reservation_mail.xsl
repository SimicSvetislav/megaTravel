<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/">
      <html>
      	<head>
	        <title>Reservation report</title>
	        <style type="text/css">
	            table {
	                font-family: serif;
	                border-collapse: collapse;
	                margin: 50px auto 50px auto;
	                width: 90%;
	            }
	            th, td {
	                text-align: left;
	                padding: 30px;
	            }
	            tr:nth-child(even){ background-color: #f2f2f2 }
	            th {
	                background-color: #4caf50; 
	                font-family: sans-serif;
	                color: white;
	            }
	            tr { border: 1px solid darkgrey; }
	            tr:hover {
	                font-style: italic;
	                background-color: #cae8cb;
	            }
	            body { font-family: sans-serif; }
	            p { text-indent: 30px; }
	            .sup {
	                vertical-align: super;
	                padding-left: 4px;
	                font-size: small;
	                text-transform: lowercase;
	            }
	        </style>
        </head>
        <body>
            <h1>Your reservation: </h1>
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