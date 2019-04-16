package org.rcsb.cif.binary.codec;

import org.rcsb.cif.binary.data.*;
import org.rcsb.cif.binary.encoding.Encoding;
import org.rcsb.cif.binary.encoding.FixedPointEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class FixedPointCodec {
    private static final Logger logger = LoggerFactory.getLogger(FixedPointCodec.class);

    public Int32Array encode(FloatArray data, FixedPointEncoding encoding) {
        int srcType = data.getType();
        int factor = encoding.getFactor();

        int[] outputArray = DoubleStream.of(data.getData())
                .mapToInt(d -> (int) Math.round(d * factor))
                .toArray();

        LinkedList<Encoding> enc = new LinkedList<>(data.getEncoding());
        encoding.setSrcType(srcType);
        enc.add(encoding);

        logger.debug("encoding by {}: {}[{}] to {}[{}]", encoding, data.getClass().getSimpleName(), data.length(),
                Int32Array.class.getSimpleName(), outputArray.length);
        return EncodedDataFactory.int32Array(outputArray, enc);
    }

    public FloatArray decode(Int32Array data, FixedPointEncoding encoding) {
        int[] input = data.getData();
        int srcType = encoding.getSrcType();

        double f = 1.0 / encoding.getFactor();

        double[] outputArray = IntStream.of(input)
                .mapToDouble(i -> f * i)
                .toArray();

        logger.debug("decoding by {}: {}[{}] to {}[{}]", encoding, data.getClass().getSimpleName(), data.length(),
                srcType == 33 ? Float64Array.class.getSimpleName() : Float32Array.class.getSimpleName(),
                outputArray.length);

        return srcType == 32 ? EncodedDataFactory.float32Array(outputArray, data.getEncoding()) :
                EncodedDataFactory.float64Array(outputArray, data.getEncoding());
    }
}
