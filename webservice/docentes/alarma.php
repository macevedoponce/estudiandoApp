<?php

include_once 'db.php';

class Alarma extends DB{
    
    function obtenerTareas(){
        $query = $this->connect()->query('SELECT * FROM docentes ');
        return $query;
    }

    function obtenerTarea($id){
        $query = $this->connect()->prepare('SELECT * FROM docentes WHERE docDni = :docDni');
        $query->execute(['docDni' => $id]);
        return $query;
    }

    function obtenerAlarmaUsuario($usuarioID){
        $query = $this->connect()->prepare('SELECT * FROM alarmas WHERE usuarioID = :usuarioID');
        $query->execute(['usuarioID' => $usuarioID]);
        return $query;
    }
}

?>