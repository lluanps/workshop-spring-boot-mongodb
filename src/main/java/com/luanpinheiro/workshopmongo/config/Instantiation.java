package com.luanpinheiro.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.luanpinheiro.workshopmongo.domain.Post;
import com.luanpinheiro.workshopmongo.domain.User;
import com.luanpinheiro.workshopmongo.dto.AuthorDTO;
import com.luanpinheiro.workshopmongo.repository.PostRepository;
import com.luanpinheiro.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("19/12/2022"), "Me matriculei no curso ADS da Uninter!!!", "Alem disso tbm vou fazer o bootcamp da DIO!", new AuthorDTO(alex));
		Post post2 = new Post(null, sdf.parse("21/12/2022"), "Bom dia", "quase terminando o curso do nelio alves sobre java POO", new AuthorDTO(alex));
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		alex.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(alex);
	}

}
