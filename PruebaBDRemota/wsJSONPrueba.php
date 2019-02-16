<?PHP
$hostname_localhost="localhost";
$database_localhost="prueba";
$username_localhost="root";
$password_localhost="";

$json=array();
 if(isset($_GET["datos"])){
  $datos=$_GET['datos'];

  $conexion = new mysqli($hostname_localhost, $username_localhost, $password_localhost, $database_localhost);





  $insert="INSERT INTO info(datos) VALUES ('{$datos}')";



  if($conexion->query($insert)===TRUE){


   $resultado = $conexion->query("SELECT * FROM info WHERE datos = '{$datos}'");
   //$resultado=mysqli_query($conexion, $consulta);

   if($registro=mysqli_fetch_array($resultado)){
    $json['info'][]=$registro;
   }
   mysqli_close($conexion);
   echo json_encode($json);

  }else{
   $resulta["datos"]="NO registra";
   $json['info'][]=$resulta;
   echo json_encode($json);
  }
 }else{
  $resulta["datos"]="WS NO RETORNA";
  $json['info'][]=$resulta;
  echo json_encode($json);
 }
?>﻿
