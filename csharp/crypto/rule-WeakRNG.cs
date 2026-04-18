// License: LGPL-3.0 License (c) security-code-scan
using System.Security.Cryptography;

class WeakRNG
{
    static string UnsafeRNG()
    {
        var rnd = new Random();
        byte[] buffer = new byte[16];
        // ruleid: csharp_crypto_rule-WeakRNG
        rnd.NextBytes(buffer);
        // ruleid: csharp_crypto_rule-WeakRNG
        rnd.Next();
        // ruleid: csharp_crypto_rule-WeakRNG
        rnd.NextDouble();
        // ...and so forth.
        return BitConverter.ToString(buffer);
    }
    
    static string Safe()
    {
        var rnd = RandomNumberGenerator.Create();
        byte[] buffer = new byte[16];
        rnd.GetBytes(buffer);
        return BitConverter.ToString(buffer);
    }
}
