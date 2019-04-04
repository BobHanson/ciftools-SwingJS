package org.rcsb.cif.binary.codec;

import org.rcsb.cif.binary.array.*;

public class DeltaCodec extends Codec<IntArray, IntArray> {
    public static final String KIND = "Delta";
    public static final DeltaCodec DELTA_CODEC = new DeltaCodec();

    private DeltaCodec() {
        super(KIND);
    }

    public static IntArray decode(CodecData<IntArray> codecData) {
        return DELTA_CODEC.decodeInternally(codecData);
    }

    @Override
    protected CodecData<IntArray> encodeInternally(CodecData data) {
        IntArray input = (IntArray) data.getData();
        if (input instanceof Uint8Array || input instanceof Uint16Array || input instanceof Uint32Array) {
            throw new IllegalArgumentException("Only signed integer types can be encoded using delta encoding.");
        }

        int srcType;
        if (data.getParameters().get("srcType") == null) {
            input = new Int32Array(input.getArray());
            srcType = Int32Array.TYPE;
        } else {
            srcType = (int) data.getParameters().get("srcType");
        }

        int[] inputArray = input.getArray();
        if (inputArray.length == 0) {
            return CodecData.of(IntArray.get(srcType, 0))
                    .addParameter("origin", 0)
                    .addParameter("srcType", srcType)
                    .create(KIND);
        }

        IntArray output = IntArray.get(srcType, inputArray.length);
        int[] outputArray = output.getArray();
        int origin = inputArray[0];
        outputArray[0] = inputArray[0];
        for (int i = 1; i < inputArray.length; i++) {
            outputArray[i] = inputArray[i] - inputArray[i - 1];
        }
        outputArray[0] = 0;

        return CodecData.of(output)
                .addParameter("origin", origin)
                .addParameter("srcType", srcType)
                .create(KIND);
    }

    @Override
    protected IntArray decodeInternally(CodecData<IntArray> data) {
        int[] input = data.getData().getArray();
        int origin = (int) data.getParameters().get("origin");
        int srcType = (int) data.getParameters().get("srcType");
        IntArray output = IntArray.get(srcType, input.length);

        int n = input.length;
        if (n == 0) {
            return output;
        }

        int[] outputArray = output.getArray();
        outputArray[0] = input[0] + origin;
        for (int i = 1; i < n; ++i) {
            outputArray[i] = input[i] + outputArray[i - 1];
        }
        return output;
    }
}
