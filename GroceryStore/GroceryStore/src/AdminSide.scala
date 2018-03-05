//THIS IS ADMIN SIDE WHICH GETS DATA FROM USER AND PASS DISCOUNTS TO USER VIA SOCKET PROGRAMMING

import java.io._
import java.net.{ServerSocket, Socket}
import javax.swing.{JFrame, JOptionPane}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object AdminSide extends JFrame{

  var hm:mutable.HashMap[Int,Double]=mutable.HashMap()
  var ss:ServerSocket=_
  var sckt:Socket=_
  var dos:java.io.DataOutputStream=_
  var dis:java.io.DataInputStream=_
  var lb:ListBuffer[dataStruct]=ListBuffer()


  def startConn(): Unit = {
    println("Connection Started")
    ss=new ServerSocket(9000)
    sckt=ss.accept()
    JOptionPane.showMessageDialog(this,"User Connected")

  }

  def startStreams() = {
    dos= new DataOutputStream(sckt.getOutputStream)
    dis= new DataInputStream(sckt.getInputStream)

  }

  def validateData(i: Int, q: Double): String = i match {
    case 0 => {if(q>=4)
                return "soap"
                else
                return "empty"
              }
    case 1 => {if(q>=2)
      return "old"
    else
      return "empty"
    }

    case _ => {return "empty"}
  }

  def readDataViaStreams(): Unit = {


    dos.writeUTF("helloadmin")
    while (true) {
      println("Connection Startedhhhhhhhhhh")
      var str = dis.readUTF()
      if (str.matches("hello")) {
//        println("Hiiii there")
        var i = dis.readInt()
        var q = dis.readDouble()
        val g = validateData(i, q)
//        println(g+" gsgsgsgsgsgs")
        dos.writeUTF(g)


      }
    }
  }


  def importXml() = {
    lb=readFromXml.importdata()

  }

  def mapDiscounts() = {
    hm.put(0,4.0) //Bath Soap
    hm.put(1,2.0) //old spice
    hm.put(4,3.0) //red bulls
  }

  def main(args: Array[String]): Unit = {
    importXml()
    mapDiscounts()
    startConn()
    startStreams()
    readDataViaStreams()

  }

}
