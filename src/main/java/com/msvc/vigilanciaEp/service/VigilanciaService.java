package com.msvc.vigilanciaEp.service;


import com.msvc.vigilanciaEp.model.MesCasos;
import com.msvc.vigilanciaEp.model.Vigilancia;
import com.msvc.vigilanciaEp.model.VirusCasos;
import com.msvc.vigilanciaEp.model.BacteriasCasos;
import com.msvc.vigilanciaEp.repository.VigilanciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.logging.Logger;


@Service
@Slf4j /*LOG*/
public class VigilanciaService {

    /*INYECTAMOS LOS REPOSITORIOS*/
    @Autowired
    private VigilanciaRepository vigilanciaRepository;

    private static final Logger logger = Logger.getLogger(VigilanciaService.class.getName());

    public void guardarMes(Vigilancia vigilancia) {
        vigilanciaRepository.save(vigilancia);
    }
    //bola bola

    public List<Vigilancia> buscarPorNombreAlcaldia(String alcaldia) {

        return vigilanciaRepository.findByAlcaldia(alcaldia);
    }


    /*METODO PARA CRRAR UNA NUEVA ALCALDIA*/

    public ResponseEntity<String> agregarAlcaldia(Vigilancia nuevaAlcaldia) {
        // Buscamos si existe la alcaldía
        List<Vigilancia> alcaldias = vigilanciaRepository.findByAlcaldia(nuevaAlcaldia.getAlcaldia());

        if (alcaldias != null && !alcaldias.isEmpty()) {
            logger.severe("La alcaldía ya existe en la base de datos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La alcaldía ya existe en la base de datos");
        } else {
            vigilanciaRepository.save(nuevaAlcaldia);
            logger.severe("Alcaldía agregada");
            return ResponseEntity.ok("Alcaldía agregada correctamente");
        }
    }



    /*metodo*/
    public void calcularCasosPredectibles(String alcaldia,String nombreVirus, String mes) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia1 : vigilancia) {
            for (VirusCasos virus : vigilancia1.getVirusCasos()) {
                if (virus.getNombreVirus().equals(nombreVirus)) {
                    List<MesCasos> casos = virus.getCasos();

                    // // Buscar el mes anterior al mes actual

                    MesCasos mesAnterior = null;
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // El mes anterior es el mes anterior al mes actual
                            break;
                        }
                        // Asigna el mes anterior en cada iteración
                        mesAnterior = m;
                    }

                    if (mesAnterior != null) {
                        // Realiza el cálculo de los casos predecibles
                        double alpha = 0.2;
                        int casosPredecibles = (int) Math.round(alpha * mesAnterior.getCasosHistoricos()
                                + (1 - alpha) * mesAnterior.getCasosPredictibles());

                        // Busca el mes actual
                        MesCasos mesActual = null;
                        for (MesCasos m : casos) {
                            if (m.getMes().equals(mes)) {
                                mesActual = m;
                                break;
                            }
                        }

                        if (mesActual != null) {
                            // Establece los casos predecibles en el mes actual
                            mesActual.setCasosPredictibles(casosPredecibles);
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        } else {
                            logger.severe("Error al predecir: mes actual no encontrado");
                        }
                    } else {
                        logger.severe("Error al hacer la predicción: mes anterior no encontrado");
                    }
                }

            }
        }

    }

    /*para iztapalapa agregar mas virus*/
    public void agregarVirusIztapalapa(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Iztapalapa");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaIztapalapa = optionalVigilancia.get(0);
            vigilanciaIztapalapa.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaIztapalapa);

        } else {
            //si no hay vigilancias para iztapalapa
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusIztacalco(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Iztacalco");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaIztacalco = optionalVigilancia.get(0);
            vigilanciaIztacalco.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaIztacalco);

        } else {
            //si no hay vigilancias para iztacalco
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusGustavoAmadero(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Gustavo A Madero");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaGustavoAmadero = optionalVigilancia.get(0);
            vigilanciaGustavoAmadero.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaGustavoAmadero);

        } else {
            //si no hay vigilancias para vigilanciaGustavoAmadero
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusAlvaroObregon(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("alvaroObregon");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaAlvaroObregon = optionalVigilancia.get(0);
            vigilanciaAlvaroObregon.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaAlvaroObregon);

        } else {
            //si no hay vigilancias para vigilanciaAlvaroObregon
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusAzcapotzalco(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Azcapotzalco");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaAzcapotzalco = optionalVigilancia.get(0);
            vigilanciaAzcapotzalco.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaAzcapotzalco);

        } else {
            //si no hay vigilancias para vigilanciaAzcapotzalco
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusCoyoacan(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Coyoacan");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaCoyoacan = optionalVigilancia.get(0);
            vigilanciaCoyoacan.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaCoyoacan);

        } else {
            //si no hay vigilancias para vigilanciaCoyoacan
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusCuajimalpa(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Cuajimalpa");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaCuajimalpa = optionalVigilancia.get(0);
            vigilanciaCuajimalpa.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaCuajimalpa);

        } else {
            //si no hay vigilancias para vigilanciaCuajimalpa
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusMagdalenaContreras(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("MagdalenaContreras");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaMagdalenaContreras = optionalVigilancia.get(0);
            vigilanciaMagdalenaContreras.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaMagdalenaContreras);

        } else {
            //si no hay vigilancias para vigilanciaMagdalenaContreras
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusTlalpan(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Tlalpan");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaTlalpan = optionalVigilancia.get(0);
            vigilanciaTlalpan.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaTlalpan);

        } else {
            //si no hay vigilancias para vigilanciaTlalpan
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusBenitoJuarez(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("BenitoJuarez");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaBenitoJuarez = optionalVigilancia.get(0);
            vigilanciaBenitoJuarez.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaBenitoJuarez);

        } else {
            //si no hay vigilancias para vigilanciaBenitoJuarez
            logger.severe("no se encuentran vigilancias ");
        }

    }


    public void agregarVirusMiguelHidalgo(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("MiguelHidalgo");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaMiguelHidalgo = optionalVigilancia.get(0);
            vigilanciaMiguelHidalgo.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaMiguelHidalgo);

        } else {
            //si no hay vigilancias para vigilanciaMiguelHidalgo
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusMilpaAlta(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("MilpaAlta");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaMilpaAlta = optionalVigilancia.get(0);
            vigilanciaMilpaAlta.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaMilpaAlta);

        } else {
            //si no hay vigilancias para vigilanciaMilpaAlta
            logger.severe("no se encuentran vigilancias ");
        }

    }


    public void agregarVirusTlahuac(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Tlahuac");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaTlahuac = optionalVigilancia.get(0);
            vigilanciaTlahuac.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaTlahuac);

        } else {
            //si no hay vigilancias para vigilanciaTlahuac
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusVenustianoCarranza(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("VenustianoCarranza");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaVenustianoCarranza = optionalVigilancia.get(0);
            vigilanciaVenustianoCarranza.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaVenustianoCarranza);

        } else {
            //si no hay vigilancias para vigilanciaVenustianoCarranza
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusXochimilco(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Xochimilco");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaXochimilco = optionalVigilancia.get(0);
            vigilanciaXochimilco.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaXochimilco);

        } else {
            //si no hay vigilancias para vigilanciaXochimilco
            logger.severe("no se encuentran vigilancias ");
        }

    }

    public void agregarVirusCuahutemoc(VirusCasos virusCasos) {
        List<Vigilancia> optionalVigilancia = vigilanciaRepository.findByAlcaldia("Xochimilco");

        if (optionalVigilancia != null) {

            Vigilancia vigilanciaCuahutemoc = optionalVigilancia.get(0);
            vigilanciaCuahutemoc.getVirusCasos().add(virusCasos);
            vigilanciaRepository.save(vigilanciaCuahutemoc);

        } else {
            //si no hay vigilancias para vigilanciaCuahutemoc
            logger.severe("no se encuentran vigilancias ");
        }

    }



    /*agregar un nuevo mes a iztapalapa y al virus*/
    public void agregarMes(String alcaldia, String nombreVirus, MesCasos nuevoMes) {
        List<Vigilancia> listVigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia : listVigilancia) {
            //busacmis el virus por su nombre
            List<VirusCasos> virusCasos = vigilancia.getVirusCasos();

            for (VirusCasos virusCasos1 : virusCasos) {
                if (virusCasos1.getNombreVirus().equals(nombreVirus)) {
                    //agregamos el nuevo mes
                    List<MesCasos> casos = virusCasos1.getCasos();
                    casos.add(nuevoMes);

                    //guardamos
                    vigilanciaRepository.save(vigilancia);
                    return;
                }
            }

        }

    }

    /*para iztapalapa*/
    public void deletePorNombreVirus(String nombreVirus) {
        // Buscar la vigilancia por alcaldía (supongamos que se llama "Iztapalapa")
        List<Vigilancia> vigilancias = vigilanciaRepository.findByAlcaldia("Iztapalapa");

        for (Vigilancia vigilancia : vigilancias) {
            List<VirusCasos> virusCasos = vigilancia.getVirusCasos();

            // Filtrar los virus y mantener solo aquellos cuyo nombre no coincida con el que se va a eliminar
            List<VirusCasos> virusFiltrados = virusCasos.stream()
                    .filter(virus -> !virus.getNombreVirus().equals(nombreVirus))
                    .collect(Collectors.toList());

            // Actualizar la lista de virus en la vigilancia
            vigilancia.setVirusCasos(virusFiltrados);

            // Guardar los cambios en la base de datos
            vigilanciaRepository.save(vigilancia);
        }
    }

    /*actualizar casos historicos*/
    public void actualizarCasosHistoricos(String alcaldia, String nombreVirus, String mes, int nuevosCasosHistoricos) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);


        for (Vigilancia vigilancia1 : vigilancia) {
            for (VirusCasos virus : vigilancia1.getVirusCasos()) {
                if (virus.getNombreVirus().equals(nombreVirus)) {
                    List<MesCasos> casos = virus.getCasos();

                    // Buscamos el mes específico
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // Actualizar casosHistoricos para el mes específico
                            m.setCasosHistoricos(nuevosCasosHistoricos);
                            // Guardar los cambios en el mongo
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        }
                    }
                }
            }
        }
    }

    /***********************BACTERIAS************************************************************/

    /*agregar bacterias*/

    public void agregarBacteriasAAlcaldia(String alcaldia, List<BacteriasCasos> bacteriasCasosList) {
        // Buscar la lista de vigilancias por alcaldía
        List<Vigilancia> vigilancias = vigilanciaRepository.findByAlcaldia(alcaldia);

        if (!vigilancias.isEmpty()) {
            // Recorrer la lista de vigilancias para agregar las bacterias a cada una
            for (Vigilancia vigilancia : vigilancias) {
                // Obtener la lista actual de bacterias de la vigilancia
                List<BacteriasCasos> bacteriasActuales = vigilancia.getBacteriasCasos();

                // Verificar si la lista actual es nula y crear una nueva si es necesario
                if (bacteriasActuales == null) {
                    bacteriasActuales = new ArrayList<>();
                }

                // Agregar las nuevas bacterias a la lista existente
                bacteriasActuales.addAll(bacteriasCasosList);

                // Actualizar la lista de bacterias en la vigilancia
                vigilancia.setBacteriasCasos(bacteriasActuales);

                // Guardar los cambios en la base de datos
                vigilanciaRepository.save(vigilancia);
            }
        } else {
            logger.severe("No se encontró ninguna vigilancia para la alcaldía especificada: " + alcaldia);
        }
    }




    public void agregarMesBacterias(String alcaldia, String nombreBacterias, MesCasos nuevoMes) {
        List<Vigilancia> listVigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia : listVigilancia) {
            //busacmis el virus por su nombre
            List<BacteriasCasos> bacteriasCasos = vigilancia.getBacteriasCasos();

            for (BacteriasCasos bacteriasCasos1 : bacteriasCasos) {
                if (bacteriasCasos1.getNombreBacterias().equals(nombreBacterias)) {
                    //agregamos el nuevo mes
                    List<MesCasos> casos = bacteriasCasos1.getCasosBacterias();
                    casos.add(nuevoMes);

                    //guardamos
                    vigilanciaRepository.save(vigilancia);
                    return;
                }
            }

        }

    }

    public void actualizarCasosHistoricosBacterias(String alcaldia, String nombreBacterias, String mes, int nuevosCasosHistoricos) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);


        for (Vigilancia vigilancia1 : vigilancia) {
            for (BacteriasCasos bacterias : vigilancia1.getBacteriasCasos()) {
                if (bacterias.getNombreBacterias().equals(nombreBacterias)) {
                    List<MesCasos> casos = bacterias.getCasosBacterias();

                    // Buscamos el mes específico
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // Actualizar casosHistoricos para el mes específico
                            m.setCasosHistoricos(nuevosCasosHistoricos);

                            // Guardar los cambios en el mongo
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        }
                    }
                }
            }
        }
    }
    /*Calcular casos bacteria*/
    public void calcularCasosPredectiblesBacterias(String alcaldia ,String nombreBacterias, String mes) {
        List<Vigilancia> vigilancia = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia1 : vigilancia) {
            for (BacteriasCasos bacterias : vigilancia1.getBacteriasCasos()) {
                if (bacterias.getNombreBacterias().equals(nombreBacterias)) {
                    List<MesCasos> casos = bacterias.getCasosBacterias();

                    // // Buscar el mes anterior al mes actual

                    MesCasos mesAnterior = null;
                    for (MesCasos m : casos) {
                        if (m.getMes().equals(mes)) {
                            // El mes anterior es el mes anterior al mes actual
                            break;
                        }
                        // Asigna el mes anterior en cada iteración
                        mesAnterior = m;
                    }

                    if (mesAnterior != null) {
                        // Realiza el cálculo de los casos predecibles
                        double alpha = 0.2;
                        int casosPredecibles = (int) Math.round(alpha * mesAnterior.getCasosHistoricos()
                                + (1 - alpha) * mesAnterior.getCasosPredictibles());

                        // Busca el mes actual
                        MesCasos mesActual = null;
                        for (MesCasos m : casos) {
                            if (m.getMes().equals(mes)) {
                                mesActual = m;
                                break;
                            }
                        }

                        if (mesActual != null) {
                            // Establece los casos predecibles en el mes actual
                            mesActual.setCasosPredictibles(casosPredecibles);
                            vigilanciaRepository.save(vigilancia1);
                            return;
                        } else {
                            logger.severe("Error al predecir: mes actual no encontrado");
                        }
                    } else {
                        logger.severe("Error al hacer la predicción: mes anterior no encontrado");
                    }
                }

            }
        }

    }

    public void deletePorNombreBacteria(String alcaldia,String nombreBacterias) {
        // Buscar la vigilancia por alcaldía (supongamos que se llama "Iztapalapa")
        List<Vigilancia> vigilancias = vigilanciaRepository.findByAlcaldia(alcaldia);

        for (Vigilancia vigilancia : vigilancias) {
            List<BacteriasCasos> bacteriasCasos = vigilancia.getBacteriasCasos();

            // Filtrar los bacterias y mantener solo aquellos cuyo nombre no coincida con el que se va a eliminar
            List<BacteriasCasos> bacteriasFiltrados = bacteriasCasos.stream()
                    .filter(bacterias -> !bacterias.getNombreBacterias().equals(nombreBacterias))
                    .collect(Collectors.toList());

            // Actualizar la lista de bacterias en la vigilancia
            vigilancia.setBacteriasCasos(bacteriasFiltrados);

            // Guardar los cambios en la base de datos
            vigilanciaRepository.save(vigilancia);
        }
    }




}
