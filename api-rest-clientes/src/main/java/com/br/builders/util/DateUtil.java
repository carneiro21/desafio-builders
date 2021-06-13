package com.br.builders.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil {
	
	/**
	 * Metodo para calcular o intervalo em MiliSegundos entre duas datas.A data fim deve ser maior que a data inicio
	 * @param inicio Data inicial
	 * @param fim Data final 
	 * @return numero de miisegundos entre as duas datas
	 */
	public static long getIntervalo(java.util.Date inicio, java.util.Date fim) {
		if (fim.compareTo(inicio) < 0) {
			throw new RuntimeException("Data fim menor que data inicio");
		}
		return (fim.getTime() - inicio.getTime());
	}

	/**
	 * Metodo para calcular o numero de dias entre duas datas. A data fim deve ser maior que a data inicio 
	 * @param inicio data inicial
	 * @param fim data final
	 * @return inteiro de numero de dias entre elas ( sem forem no mesmo dia retorna 0)
	 */
	public static int getIntervaloDia(java.util.Date inicio, java.util.Date fim) {
		long intervalo = getIntervalo(inicio, fim);
		return (int) (intervalo / (24*3600*1000));
	}

	/**
	 * Metodo para calcular o numero de segundos entre duas datas
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public static int getIntervaloSegundo(java.util.Date inicio, java.util.Date fim) {
		long intervalo = getIntervalo(inicio, fim);
		return (int) (intervalo / (1000));
	}
	/**
	 * Metodo para calcular o numero de minutos entre duas datas
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public static int getIntervaloMinuto(java.util.Date inicio, java.util.Date fim) {
		long intervalo = getIntervalo(inicio, fim);
		return (int) (intervalo / (60 * 1000));
	}
	/**
	 * Metodo para calcular o numero de horas entre duas datas
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public static int getIntervaloHoras(java.util.Date inicio, java.util.Date fim) {
			long intervalo = getIntervalo(inicio, fim);
			return (int) (intervalo / (3600 * 1000));
	}

	/**
	 * Método para retornar o dia corrente
	 * @return int com o dia corrente
	 */
	public static int getCurrentDay(){
	    Calendar calendar = new GregorianCalendar();	    
	    return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * Método para retornar o mês corrente
	 * @return int com mes corrente (lembrando que é janeiro é zero)
	 */
	public static int getCurrentMonth(){
	    Calendar calendar = new GregorianCalendar();	    
	    return calendar.get(Calendar.MONTH);
	}
	/**
	 * Metodo para retornar o ano corrente
	 * @return int com o valor do ano corrente
	 */
	public static int getCurrentYear(){
	    Calendar calendar = new GregorianCalendar();	    
	    return calendar.get(Calendar.YEAR);
	}
	/**
	 * metodo para retorna o dia de uma data
	 * @param date long com timeMilis da data
	 * @return int com o valor do dia da data
	 */
	public static int getDay(long date){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(new java.util.Date(date));
	    return calendar.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * metodo para retorna o mes de uma data
	 * @param date long com timeMilis da data
	 * @return int com o valor do mes da data
	 */
	public static int getMonth(long date){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(new java.util.Date(date));
	    return calendar.get(Calendar.MONTH);
	}
	/**
	 * metodo para retorna o ano de uma data
	 * @param date long com timeMilis da data
	 * @return int com o valor do ano da data
	 */
	public static int getYear(long date){
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(new java.util.Date(date));
	    return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * Retorna a data String no formato yyyy-MM-dd'T'HH:mm:ss.SSS'Z' padrão da
	 * documentação do SPI BAcen
	 * 
	 * @return data String
	 */
	public static Date getDataHoraAtual() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		format.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
		String data = format.format(new Date());
		try {
			return format.parse(data);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getDataHoraAjustada(int horas, int minutos, int segundos) {
		Date dataHora = getDataHoraAtual();
		ZonedDateTime dateTime = ZonedDateTime.ofInstant(dataHora.toInstant(), ZoneId.systemDefault());
		if (horas < 0)
			dateTime = dateTime.minus((horas * -1), ChronoUnit.HOURS);
		if (horas > 0)
			dateTime = dateTime.plus(horas, ChronoUnit.HOURS);
		if (minutos < 0)
			dateTime = dateTime.minus((minutos * -1), ChronoUnit.MINUTES);
		if (minutos > 0)
			dateTime = dateTime.plus(minutos, ChronoUnit.MINUTES);
		if (segundos < 0)
			dateTime = dateTime.minus((segundos * -1), ChronoUnit.SECONDS);
		if (segundos > 0)
			dateTime = dateTime.plus(segundos, ChronoUnit.SECONDS);
		return Date.from(dateTime.toInstant());
	}
	
	public static LocalDate transformStringToLocalDate(String data) {
		if (data == null) {
			return null;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		LocalDate dateTime = LocalDate.parse(data, formatter);
		return dateTime;
	}

}
