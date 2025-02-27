package br.com.publishmessage.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String email;
	
}
