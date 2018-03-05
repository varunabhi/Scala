import java.io.{File, FileInputStream, FileOutputStream, IOException}

import org.w3c.dom
import org.w3c.dom.Element

import scala.xml.{Document, SAXException}
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

import scala.collection.mutable

  class XMLParser {

    def data(hm: mutable.HashMap[Int, Double]) {
      val xmlFile = new File("/home/varun/IdeaProjects/GroceryStore/src/GroceryData.xml")
      val dbFactory = DocumentBuilderFactory.newInstance
      var dBuilder: DocumentBuilder = null
      try {
        dBuilder = dbFactory.newDocumentBuilder
        val doc = dBuilder.parse(xmlFile)
        doc.getDocumentElement.normalize
        //update attribute value
        //        updateAttributeValue(doc)
        //update Element value
        updateElementValue(doc, hm)
        //delete element
        //        deleteElement(doc)
        //add new element
        //        addElement(doc)
        //write the updated document to file or console
        doc.getDocumentElement.normalize
        val transformerFactory = TransformerFactory.newInstance
        val transformer = transformerFactory.newTransformer
        val source = new DOMSource(doc)
        val result = new StreamResult(new File("/home/varun/IdeaProjects/GroceryStore/src/Storeupdated.xml"))
        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
        println("XML file updated successfully")
      } catch {
        case a: SAXException => a.printStackTrace()
        case b: ParserConfigurationException => b.printStackTrace()
        case c: IOException => c.printStackTrace()
        case d: TransformerException => d.printStackTrace()
      }

      copyFileContents()
    }

    def copyFileContents(): Unit = {
      var ins:FileInputStream = null;
      var outs:FileOutputStream = null;
      try {
        var infile =new File("/home/varun/IdeaProjects/GroceryStore/src/Storeupdated.xml")
        var outfile =new File("/home/varun/IdeaProjects/GroceryStore/src/GroceryData.xml")
        ins = new FileInputStream(infile)
        outs = new FileOutputStream(outfile)

        import java.io.PrintWriter
        val writer = new PrintWriter(outfile)
        writer.print("")
        writer.close()


        var buffer:Array[Byte]= new Array[Byte](1024)
        var length:Int=ins.read(buffer)

        while (length> 0) {
          outs.write(buffer, 0, length);
          length=ins.read(buffer)
        }
        ins.close()
        outs.close()
        System.out.println("File copied successfully!!");
      } catch {
        case e:IOException => e.printStackTrace()
      }
    }
    //    private def addElement(doc: dom.Document ): Unit = {
    //      val employees = doc.getElementsByTagName("Employee")
    //      var emp:Element = null
    //      //loop for each employee
    //      var i = 0
    //      while ( {
    //        i < employees.getLength
    //      }) {
    //        emp = employees.item(i).asInstanceOf[Nothing]
    //        val salaryElement = doc.createElement("salary")
    //        salaryElement.appendChild(doc.createTextNode("10000"))
    //        emp.appendChild(salaryElement)
    //
    //        {
    //          i += 1; i - 1
    //        }
    //      }
    //    }

    //    private def deleteElement(doc: dom.Document): Unit = {
    //      val employees = doc.getElementsByTagName("Employee")
    //      var emp:Element = null
    //      var i = 0
    //      while ( {
    //        i < employees.getLength
    //      }) {
    //        emp = employees.item(i).asInstanceOf[Nothing]
    //        val genderNode = emp.getElementsByTagName("gender").item(0)
    //        emp.removeChild(genderNode)
    //
    //        {
    //          i += 1; i - 1
    //        }
    //      }
    //    }

    def updateElementValue(doc: dom.Document, mkp: mutable.HashMap[Int, Double]): Unit = {
      val quantity = doc.getElementsByTagName("quantity")
      var qty: Element = null
      val lst = mkp.keySet.toList

      val length = quantity.getLength
      for (i <- mkp) {
        qty = quantity.item(i._1).asInstanceOf[Element]
        val name = qty.getElementsByTagName("stock").item(0).getFirstChild
        name.setNodeValue((name.getNodeValue.toDouble - (i._2)).toString)


      }

      //    private def updateAttributeValue(doc: dom.Document): Unit = {
      //      val employees = doc.getElementsByTagName("Employee")
      //      var emp:Element = null
      //
      //      val length=employees.getLength
      //      for(i <- 0 until length){
      //        emp = employees.item(i).asInstanceOf[Nothing]
      //        val gender = emp.getElementsByTagName("gender").item(0).getFirstChild.getNodeValue
      //        if (gender.equalsIgnoreCase("male")) { //prefix id attribute with M
      //          emp.setAttribute("id", "M" + emp.getAttribute("id"))
      //        }
      //        else { //prefix id attribute with F
      //          emp.setAttribute("id", "F" + emp.getAttribute("id"))
      //        }
      //
      //
      //    }
      //  }


    }
  }

