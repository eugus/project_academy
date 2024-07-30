package br.com.versao2.Academia;

import br.com.versao2.Academia.DTO.AlunoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {


	private static Logger log = LoggerFactory.getLogger(AcademiaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);

		log.info("Primeiro log");

			AlunoDTO alunoDto = new AlunoDTO();
			alunoDto.setPeso(70.0); // Certifique-se de que isso não é nulo
			alunoDto.setAltura(1.75); // Certifique-se de que isso não é nulo

			double imc =  alunoDto.getPeso() / Math.pow(alunoDto.getAltura(), 2);
			System.out.println("IMC: " + imc);
		}
	}




