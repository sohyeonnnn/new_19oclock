package com.refactoring.ilgusi.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class CommonUtil {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom random = new SecureRandom();

	public static String generateTempPassword(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int idx = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(idx));
		}
		return sb.toString();
	}

	//파일 중복체크
	public static String fileRename(String dir, String filename) {
		Path directory = Paths.get(dir);
		String name = filename.substring(0, filename.lastIndexOf('.'));
		String ext = filename.substring(filename.lastIndexOf('.'));

		int count = 0;
		String newName;

		do {
			newName = count == 0 ? name + ext : name + "(" + count + ")" + ext;
			count++;
		} while (Files.exists(directory.resolve(newName)));

		return newName;
	}
}
