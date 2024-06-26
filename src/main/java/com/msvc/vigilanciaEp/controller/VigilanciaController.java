package com.msvc.vigilanciaEp.controller;


import com.msvc.vigilanciaEp.model.MesCasos;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.model.VirusCasos;
import com.msvc.vigilanciaEp.model.BacteriasCasos;
import com.msvc.vigilanciaEp.service.VigilanciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/v1/vigilancia")
public class VigilanciaController {


    /*importante
    *
    * @RequestParam se usa para extraer los parametros de la URL o hacer consultas GET
    *@PathVariable se usa para extraer las variables de la URL  inscrustradas en la URL /usuarios/{id}
    * */
    @Autowired
    private VigilanciaService vigilanciaService;



    /*agregar alcaldia*/
    @PostMapping("/agregar-alcaldia")
    public ResponseEntity<String> agregarAlcaldias(@RequestBody Vigilancia nuevaAlcaldia){
        try{
            return vigilanciaService.agregarAlcaldia(nuevaAlcaldia);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error al agregar");
        }
    }


    @PostMapping("/createMes")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMes(@RequestBody Vigilancia vigilancia  ){

        vigilanciaService.guardarMes(vigilancia);
    }

    @PostMapping("/calcular-prediccion/{alcaldia}/{nombreVirus}/{mes}")
    public ResponseEntity<String> calcularPrediccion(@PathVariable String alcaldia,@PathVariable String nombreVirus,@PathVariable String mes){

        try{
            vigilanciaService.calcularCasosPredectibles(alcaldia,nombreVirus,mes);
            return  ResponseEntity.ok("prediccion calculada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    /* agregar virus a iztapalapa*/
    @PostMapping("/iztapalapa/virus")
    public ResponseEntity<String> agregarVirusIztapalapa(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusIztapalapa(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/iztacalco/virus")
    public ResponseEntity<String> agregarVirusIztacalco(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusIztacalco(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/gustavoAmadero/virus")
    public ResponseEntity<String> agregarVirusGustavoAmadero(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusGustavoAmadero(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/alvaroObregon/virus")
    public ResponseEntity<String> agregarVirusAlvaroObregon(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusAlvaroObregon(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/azcapotzalco/virus")
    public ResponseEntity<String> agregarVirusAzcapotzalco(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusAzcapotzalco(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/coyoacan/virus")
    public ResponseEntity<String> agregarVirusCoyoacan(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusCoyoacan(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/cuajimalpa/virus")
    public ResponseEntity<String> agregarVirusCuajimalpa(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusCuajimalpa(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/magdalenaContreras/virus")
    public ResponseEntity<String> agregarVirusMagdalenaContreras(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusMagdalenaContreras(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/tlalpan/virus")
    public ResponseEntity<String> agregarVirusTlalpan(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusTlalpan(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/cuahutemoc/virus")
    public ResponseEntity<String> agregarVirusCuahutemoc(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusCuahutemoc(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/benitoJuarez/virus")
    public ResponseEntity<String> agregarVirusBenitoJuarez(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusBenitoJuarez(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/miguelHidalgo/virus")
    public ResponseEntity<String> agregarVirusMiguelHidalgo(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusMiguelHidalgo(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/milpaAlta/virus")
    public ResponseEntity<String> agregarVirusMilpaAlta(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusMilpaAlta(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/tlahuac/virus")
    public ResponseEntity<String> agregarVirusTlahuac(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusTlahuac(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }

    @PostMapping("/venustinoCarranza/virus")
    public ResponseEntity<String> agregarVirusVenustianoCarranza(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusVenustianoCarranza(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }
    @PostMapping("/xochimilco/virus")
    public ResponseEntity<String> agregarVirusXochimilco(@RequestBody VirusCasos virusCasos){
        vigilanciaService.agregarVirusXochimilco(virusCasos);
        return ResponseEntity.ok("virus agreado exitosamente");
    }





    /*metodo GET por alcaldia*/

    @GetMapping("/buscar/{alcaldia}")
    public ResponseEntity<List<Vigilancia>> obtenerVigilanciaPorAlcaldia(@PathVariable String alcaldia){
        List<Vigilancia> vigilancia = vigilanciaService.buscarPorNombreAlcaldia(alcaldia);

        return ResponseEntity.ok().body(vigilancia);
    }
    /*insertar mes */
    @PostMapping("/alcaldia/{alcaldia}/virus/{nombreVirus}/agregar-mes")
    public ResponseEntity<String> agregarMes(@PathVariable String alcaldia, @PathVariable String nombreVirus, @RequestBody MesCasos nuevoMes){
        try{
            vigilanciaService.agregarMes(alcaldia,nombreVirus,nuevoMes);
            return ResponseEntity.ok("mes agregado");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL INSERTAR MES");
        }

    }


    @DeleteMapping("/virus/{nombreVirus}")
    public ResponseEntity<String> eliminarVirusPorNombre(@PathVariable String nombreVirus) {
        try {
            vigilanciaService.deletePorNombreVirus(nombreVirus);
            return ResponseEntity.ok("Virus eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar eliminar el virus: " + e.getMessage());
        }
    }

    /*update casos*/
    @PutMapping("/actualizar-casos-historicos/{alcaldia}/{nombreVirus}/{mes}")
    public ResponseEntity<String> actualizarCasosHistoricos(
            @PathVariable String alcaldia,
            @PathVariable String nombreVirus,
            @PathVariable String mes,
            @RequestParam int nuevosCasosHistoricos) {
        try {
            vigilanciaService.actualizarCasosHistoricos(alcaldia, nombreVirus, mes, nuevosCasosHistoricos);
            return ResponseEntity.ok("Casos históricos actualizados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar casos históricos.");
        }
    }

/**********************************************************************************************************************/
    /*****************BACTERIAS***********************/

    @PostMapping("/calcular-prediccion-bacterias/{alcaldia}/{nombreBacterias}/{mes}")
    public ResponseEntity<String> calcularPrediccionBacterias(@PathVariable String alcaldia,@PathVariable String nombreBacterias,@PathVariable String mes){

        try{
            vigilanciaService.calcularCasosPredectiblesBacterias(alcaldia,nombreBacterias,mes);
            return  ResponseEntity.ok("prediccion calculada");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }

    /* agregar BACTERIAS a una alcaldia*/
    @PostMapping("/{alcaldia}/bacterias")
    public ResponseEntity<String> agregarBacteriasAalcaldia(@PathVariable String alcaldia,@RequestBody List<BacteriasCasos> bacteriasCasos){
        vigilanciaService.agregarBacteriasAAlcaldia(alcaldia,bacteriasCasos);
        return ResponseEntity.ok("bacteria agreada exitosamente");
    }


    /*insertar mes */
    @PostMapping("/alcaldia/{alcaldia}/bacterias/{nombreBacterias}/agregar-mes-bacterias")
    public ResponseEntity<String> agregarMesBacteria(@PathVariable String alcaldia, @PathVariable String nombreBacterias, @RequestBody MesCasos nuevoMes){
        try{
            vigilanciaService.agregarMesBacterias(alcaldia,nombreBacterias,nuevoMes);
            return ResponseEntity.ok("mes agregado a la bacteria");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR AL INSERTAR MES");
        }

    }


    @DeleteMapping("/{alcaldia}/bacterias/{nombreBacterias}")
    public ResponseEntity<String> eliminarBacteriaPorNombre(@PathVariable String alcaldia,@PathVariable String nombreBacterias) {
        try {
            vigilanciaService.deletePorNombreBacteria(alcaldia,nombreBacterias);
            return ResponseEntity.ok("Virus eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar eliminar el virus: " + e.getMessage());
        }
    }

    /*update casos*/
    @PutMapping("/actualizar-casos-historicos-bacterias/{alcaldia}/{nombreBacterias}/{mes}")
    public ResponseEntity<String> actualizarCasosHistoricosBacterias(
            @PathVariable String alcaldia,
            @PathVariable String nombreBacterias,
            @PathVariable String mes,
            @RequestParam int nuevosCasosHistoricos) {
        try {
            vigilanciaService.actualizarCasosHistoricosBacterias(alcaldia, nombreBacterias, mes, nuevosCasosHistoricos);
            return ResponseEntity.ok("Casos históricos actualizados para bacterias correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar casos históricos.");
        }
    }


}
