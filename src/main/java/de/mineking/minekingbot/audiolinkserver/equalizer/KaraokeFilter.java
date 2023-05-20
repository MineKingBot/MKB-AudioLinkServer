package de.mineking.minekingbot.audiolinkserver.equalizer;

import com.github.natanbc.lavadsp.karaoke.KaraokePcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.filter.FloatPcmAudioFilter;
import com.sedmelluq.discord.lavaplayer.format.AudioDataFormat;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import java.util.Map;

public class KaraokeFilter implements Filter {
	private final double level;
	private final double band;
	private final double width;
	private final double mono;

	public KaraokeFilter(Map<String, Double> data) {
		this.level = data.get("level");
		this.band = data.get("band");
		this.width = data.get("width");
		this.mono = data.get("mono");
	}

	@Override
	public FloatPcmAudioFilter buildFilter(AudioTrack track, AudioDataFormat format, FloatPcmAudioFilter output) {
		return new KaraokePcmAudioFilter(output, format.channelCount, format.sampleRate)
				.setLevel((float) level)
				.setFilterBand((float) band)
				.setFilterWidth((float) width)
				.setMonoLevel((float) mono);
	}
}
